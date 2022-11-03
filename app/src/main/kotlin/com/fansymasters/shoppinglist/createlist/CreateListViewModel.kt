package com.fansymasters.shoppinglist.createlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fansymasters.shoppinglist.NavigationWriter
import com.fansymasters.shoppinglist.data.lists.ListDto
import com.fansymasters.shoppinglist.domain.ProcessingState
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import com.fansymasters.shoppinglist.list.usecase.CreateListActions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CreateListViewModel @Inject constructor(
    private val createListActions: CreateListActions,
    private val processingState: ProcessingStateReader<ListDto>,
    private val navigationWriter: NavigationWriter,
) : ViewModel(), ProcessingStateReader<ListDto> by processingState {
    init {
        state.filterIsInstance<ProcessingState.Success<ListDto>>()
            .onEach { navigateUp() }
            .launchIn(viewModelScope)
    }

    private fun navigateUp() {
        navigationWriter.navigateUp()
    }

    fun createList(name: String, description: String) {
        viewModelScope.launch {
            createListActions.createList(name, description)
        }
    }
}