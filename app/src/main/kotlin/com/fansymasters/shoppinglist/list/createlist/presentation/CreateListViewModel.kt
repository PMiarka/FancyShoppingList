package com.fansymasters.shoppinglist.list.createlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fansymasters.shoppinglist.common.ProgressHandler
import com.fansymasters.shoppinglist.common.commonprocessingstate.CommonProcessingStateReader
import com.fansymasters.shoppinglist.common.commonprocessingstate.CommonProcessingStateWriter
import com.fansymasters.shoppinglist.common.handleProcessing
import com.fansymasters.shoppinglist.data.lists.ListDto
import com.fansymasters.shoppinglist.domain.ProcessingState
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import com.fansymasters.shoppinglist.list.createlist.usecase.CreateListActions
import com.fansymasters.shoppinglist.list.navigation.ListsNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CreateListViewModel @Inject constructor(
    private val createListActions: CreateListActions,
    private val listsNavigation: ListsNavigation,
    private val progressHandler: ProgressHandler,
) : ViewModel() {

    fun navigateUp() {
        listsNavigation.navigateUp()
    }

    fun createList(name: String, description: String) {
        handleProcessing(progressHandler,
            onSuccess = { listsNavigation.navigateUp() }) {
            createListActions.createList(name, description)
        }
    }
}