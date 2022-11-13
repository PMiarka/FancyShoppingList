package com.fansymasters.shoppinglist.list.details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fansymasters.shoppinglist.data.lists.ListDetailsDto
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import com.fansymasters.shoppinglist.list.details.usecase.FetchListDetailsActions
import com.fansymasters.shoppinglist.list.navigation.ListsNavigation
import com.fansymasters.shoppinglist.ui.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ListDetailsViewModel @Inject constructor(
    private val actions: FetchListDetailsActions,
    private val processingState: ProcessingStateReader<ListDetailsDto>,
    private val listNavigation: ListsNavigation,
    savedState: SavedStateHandle,
) : ViewModel(),
    ProcessingStateReader<ListDetailsDto> by processingState {

    val listId = savedState.get<String>(NavigationRoutes.Lists.Arguments.LIST_ID)?.toInt() ?: -1

    init {
        viewModelScope.launch {
            actions.fetchListDetails(listId)
        }
    }

    fun addItem() {
        listNavigation.openCreateItem(listId)
    }

    fun navigateUp() {
        listNavigation.navigateUp()
    }
}