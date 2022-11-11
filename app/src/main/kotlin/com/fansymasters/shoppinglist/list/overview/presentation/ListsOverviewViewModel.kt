package com.fansymasters.shoppinglist.list.overview.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fansymasters.shoppinglist.data.lists.ListDto
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import com.fansymasters.shoppinglist.list.navigation.ListsNavigation
import com.fansymasters.shoppinglist.list.overview.usecase.FetchListsActions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ListsOverviewViewModel @Inject constructor(
    private val actions: FetchListsActions,
    private val processingState: ProcessingStateReader<List<ListDto>>,
    private val listsNavigation: ListsNavigation,
) : ViewModel(), ProcessingStateReader<List<ListDto>> by processingState {

    init {
        viewModelScope.launch {
            actions.fetchLists()
        }
    }

    fun addList() {
        listsNavigation.openCreateList()
    }

    fun openListsDetails(listId: Int) {
        listsNavigation.openListDetails(listId)
    }

    fun navigateUp() {
        listsNavigation.navigateUp()
    }
}