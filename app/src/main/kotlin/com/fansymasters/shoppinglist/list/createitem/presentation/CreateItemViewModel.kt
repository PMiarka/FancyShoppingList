package com.fansymasters.shoppinglist.list.createitem.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.fansymasters.shoppinglist.common.commonprocessingstate.CommonProcessingStateReader
import com.fansymasters.shoppinglist.common.commonprocessingstate.CommonProcessingStateWriter
import com.fansymasters.shoppinglist.common.handleProcessing
import com.fansymasters.shoppinglist.data.lists.Category
import com.fansymasters.shoppinglist.data.room.CreateListItemLocalDto
import com.fansymasters.shoppinglist.list.createitem.usecase.CreateItemActions
import com.fansymasters.shoppinglist.list.navigation.ListsNavigation
import com.fansymasters.shoppinglist.presentation.UiEvent
import com.fansymasters.shoppinglist.presentation.UiEventStateWriter
import com.fansymasters.shoppinglist.ui.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class CreateItemViewModel @Inject constructor(
    private val createItemActions: CreateItemActions,
    private val listsNavigation: ListsNavigation,
    private val commonProcessingStateWriter: CommonProcessingStateWriter,
    private val uiStateWriter: UiEventStateWriter,
    savedState: SavedStateHandle,
) : ViewModel() {
    private val listId =
        savedState.get<String>(NavigationRoutes.CommonArguments.LIST_ID)?.toInt() ?: -1

    private val initItem
        get() = CreateListItemLocalDto("", 1, 0, "", Category.OTHER, false)
    val state: MutableStateFlow<CreateListItemState> = MutableStateFlow(
        CreateListItemState.Idle(item = initItem)
    )

    fun createItem() {
        handleProcessing(
            onSuccess = {
                uiStateWriter.sendEvent(UiEvent.ShowToast("Item ${state.value.item.name} created"))
                state.value = CreateListItemState.Idle(initItem)
            },
            onError = commonProcessingStateWriter::onError
        ) {
            state.update { CreateListItemState.Progress(it.item) }
            createItemActions.createItem(item = state.value.item, listId = listId)
        }
    }

    fun onNameChanged(name: String) {
        state.update { CreateListItemState.Idle(it.item.copy(name = name)) }
    }

    fun onUnitChanged(unit: String) {
        state.update { CreateListItemState.Idle(it.item.copy(unit = unit)) }
    }

    fun onCategoryChanged(category: Category) {
        state.update { CreateListItemState.Idle(it.item.copy(category = category)) }
    }

    fun onQuantityChanged(quantity: Int) {
        state.update { CreateListItemState.Idle(it.item.copy(qty = quantity)) }
    }

    fun back() {
        listsNavigation.navigateUp()
    }
}