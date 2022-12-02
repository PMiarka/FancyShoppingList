package com.fansymasters.shoppinglist.list.createitem.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fansymasters.shoppinglist.data.lists.di.Category
import com.fansymasters.shoppinglist.domain.ProcessingState
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import com.fansymasters.shoppinglist.list.createitem.di.CreateItem
import com.fansymasters.shoppinglist.list.createitem.usecase.CreateItemActions
import com.fansymasters.shoppinglist.list.navigation.ListsNavigation
import com.fansymasters.shoppinglist.presentation.UiEvent
import com.fansymasters.shoppinglist.presentation.UiEventStateWriter
import com.fansymasters.shoppinglist.ui.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CreateItemViewModel @Inject constructor(
    private val createItemActions: CreateItemActions,
    @CreateItem private val processingState: ProcessingStateReader<Unit>,
    private val listsNavigation: ListsNavigation,
    private val uiEventStateWriter: UiEventStateWriter,
    savedState: SavedStateHandle,
) : ViewModel(), ProcessingStateReader<Unit> by processingState {
    private val listId =
        savedState.get<String>(NavigationRoutes.Lists.Arguments.LIST_ID)?.toInt() ?: -1

    init {
        state.filterIsInstance<ProcessingState.Success<Unit>>()
            .onEach { uiEventStateWriter.sendEvent(UiEvent.ShowToast("Successfully Added")) }
            .launchIn(viewModelScope)
    }

    fun createItem(name: String, unit: String, quantity: Int, category: Category) {
        viewModelScope.launch {
            createItemActions.createItem(
                name = name,
                unit = unit,
                quantity = quantity,
                category = category,
                listId = listId
            )
        }
    }
}