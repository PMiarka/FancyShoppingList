package com.fansymasters.shoppinglist.list.createitem.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.fansymasters.shoppinglist.common.commonprocessingstate.CommonProcessingStateReader
import com.fansymasters.shoppinglist.common.commonprocessingstate.CommonProcessingStateWriter
import com.fansymasters.shoppinglist.common.handleProcessing
import com.fansymasters.shoppinglist.data.lists.Category
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import com.fansymasters.shoppinglist.list.createitem.di.CreateItem
import com.fansymasters.shoppinglist.list.createitem.usecase.CreateItemActions
import com.fansymasters.shoppinglist.list.navigation.ListsNavigation
import com.fansymasters.shoppinglist.presentation.UiEventStateWriter
import com.fansymasters.shoppinglist.ui.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class CreateItemViewModel @Inject constructor(
    private val createItemActions: CreateItemActions,
    private val listsNavigation: ListsNavigation,
    private val commonProcessingStateWriter: CommonProcessingStateWriter,
    commonProcessingStateReader: CommonProcessingStateReader,
    savedState: SavedStateHandle,
) : ViewModel() {
    private val listId =
        savedState.get<String>(NavigationRoutes.CommonArguments.LIST_ID)?.toInt() ?: -1

    val state = commonProcessingStateReader.state
    fun createItem(name: String, unit: String, quantity: Int, category: Category) {
        handleProcessing(
            onSuccess = commonProcessingStateWriter::onSuccess,
            onError = commonProcessingStateWriter::onError
        ) {
            createItemActions.createItem(
                name = name,
                unit = unit,
                quantity = quantity,
                category = category,
                listId = listId
            )
        }
    }

    fun back() {
        listsNavigation.navigateUp()
    }
}