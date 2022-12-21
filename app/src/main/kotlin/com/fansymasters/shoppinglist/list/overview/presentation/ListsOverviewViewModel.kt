package com.fansymasters.shoppinglist.list.overview.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fansymasters.shoppinglist.common.GeneralErrorHandler
import com.fansymasters.shoppinglist.common.commonprocessingstate.CommonProcessingState
import com.fansymasters.shoppinglist.common.commonprocessingstate.CommonProcessingStateReader
import com.fansymasters.shoppinglist.common.commonprocessingstate.CommonProcessingStateWriter
import com.fansymasters.shoppinglist.common.handleProcessing
import com.fansymasters.shoppinglist.list.navigation.ListsNavigation
import com.fansymasters.shoppinglist.list.overview.usecase.FetchListsActions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class ListsOverviewViewModel @Inject constructor(
    private val actions: FetchListsActions,
    private val listsNavigation: ListsNavigation,
    private val generalErrorHandler: GeneralErrorHandler,
    private val processingStateReader: CommonProcessingStateReader,
    private val processingStateWriter: CommonProcessingStateWriter,
) : ViewModel() {

    val listsOverviewState =
        actions.state.combine(processingStateReader.state) { lists, processingState ->
            when (processingState) {
                CommonProcessingState.Idle -> ListsOverviewState.Idle(lists)
                CommonProcessingState.Processing -> ListsOverviewState.Loading(lists)
                is CommonProcessingState.Error -> ListsOverviewState.Error(lists, processingState.e)
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            ListsOverviewState.Idle(emptyList())
        )

    init {
        fetchDetails()
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

    fun fetchDetails() {
        processingStateWriter.onLoadingStarted()
        handleProcessing(
            onSuccess = { processingStateWriter.onSuccess() },
            onError = {
                generalErrorHandler.mapError(it)
                processingStateWriter.onError(it)
            },
        ) {
            actions.fetchLists()
        }
    }
}
