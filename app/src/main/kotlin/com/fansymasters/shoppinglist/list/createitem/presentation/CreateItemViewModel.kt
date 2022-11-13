package com.fansymasters.shoppinglist.list.createitem.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import com.fansymasters.shoppinglist.list.createitem.usecase.CreateItemActions
import com.fansymasters.shoppinglist.list.navigation.ListsNavigation
import com.fansymasters.shoppinglist.ui.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CreateItemViewModel @Inject constructor(
    private val createItemActions: CreateItemActions,
    private val processingState: ProcessingStateReader<Unit>,
    private val listsNavigation: ListsNavigation,
    savedState: SavedStateHandle,
) : ViewModel(), ProcessingStateReader<Unit> by processingState {
    private val listId =
        savedState.get<String>(NavigationRoutes.Lists.Arguments.LIST_ID)?.toInt() ?: -1

    fun navigateUp() {
        listsNavigation.navigateUp()
    }

    fun createItem(name: String) {
        viewModelScope.launch {
            createItemActions.createItem(name, listId)
        }
    }
}