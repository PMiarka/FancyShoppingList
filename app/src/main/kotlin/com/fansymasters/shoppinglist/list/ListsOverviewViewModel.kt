package com.fansymasters.shoppinglist.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fansymasters.shoppinglist.NavigationWriter
import com.fansymasters.shoppinglist.data.lists.ListDto
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import com.fansymasters.shoppinglist.list.usecase.FetchListsActions
import com.fansymasters.shoppinglist.ui.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ListsOverviewViewModel @Inject constructor(
    private val actions: FetchListsActions,
    private val processingState: ProcessingStateReader<List<ListDto>>,
    private val navigationWriter: NavigationWriter,
) : ViewModel(), ProcessingStateReader<List<ListDto>> by processingState {

    init {
        viewModelScope.launch {
            actions.fetchLists()
        }
    }

    fun addList() {
        navigationWriter.navigate(NavigationRoutes.Lists.Create)
    }
}