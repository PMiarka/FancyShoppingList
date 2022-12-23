package com.fansymasters.shoppinglist.list.details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fansymasters.shoppinglist.common.commonprocessingstate.CommonProcessingState
import com.fansymasters.shoppinglist.common.commonprocessingstate.CommonProcessingStateReader
import com.fansymasters.shoppinglist.common.commonprocessingstate.CommonProcessingStateWriter
import com.fansymasters.shoppinglist.common.handleProcessing
import com.fansymasters.shoppinglist.data.room.ListDetailsLocalDto
import com.fansymasters.shoppinglist.data.room.ListItemLocalDto
import com.fansymasters.shoppinglist.list.details.usecase.DeleteListItemActions
import com.fansymasters.shoppinglist.list.details.usecase.FetchListDetailsActions
import com.fansymasters.shoppinglist.list.details.usecase.FetchListDetailsState
import com.fansymasters.shoppinglist.list.details.usecase.UpdateListItemActions
import com.fansymasters.shoppinglist.list.navigation.ListsNavigation
import com.fansymasters.shoppinglist.searchuser.navigation.SearchUserNavigation
import com.fansymasters.shoppinglist.ui.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class ListDetailsViewModel @Inject constructor(
    private val fetchActions: FetchListDetailsActions,
    private val updateListItemActions: UpdateListItemActions,
    private val deleteItemActions: DeleteListItemActions,
    private val listNavigation: ListsNavigation,
    private val usersNavigation: SearchUserNavigation,
    private val commonProcessingStateWriter: CommonProcessingStateWriter,
    commonProcessingStateReader: CommonProcessingStateReader,
    savedState: SavedStateHandle,
) : ViewModel() {

    val state =
        fetchActions.state.combine(commonProcessingStateReader.state) { details, processingState ->
            FetchListDetailsState(processingState, details)
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            FetchListDetailsState(
                CommonProcessingState.Idle,
                ListDetailsLocalDto("", 0, "", emptyList(), emptyList())
            )
        )

    val listId =
        savedState.get<String>(NavigationRoutes.CommonArguments.LIST_ID)?.toInt() ?: -1

    init {
        fetchDetails()
    }

    fun fetchDetails() {
        handleProcessing(
            onError = commonProcessingStateWriter::onError,
            onSuccess = commonProcessingStateWriter::onSuccess
        ) {
            commonProcessingStateWriter.onLoadingStarted()
            fetchActions.fetchListDetails(listId)
        }
    }

    fun setItemFinished(listItemDto: ListItemLocalDto) {
        handleProcessing(
            onSuccess = { fetchDetails() },
            onError = commonProcessingStateWriter::onError
        ) {
            updateListItemActions.setItemFinished(listItemDto)
        }
    }

    fun deleteItem(listItemDto: ListItemLocalDto) {
        handleProcessing(
            onSuccess = { fetchDetails() },
            onError = commonProcessingStateWriter::onError
        ) {
            deleteItemActions.deleteItem(listItemDto.id)
        }
    }


    fun addItem() {
        listNavigation.openCreateItem(listId)
    }

    fun openSearchUsers(listId: Int) {
        usersNavigation.openSearchUser(listId)
    }

    fun navigateUp() {
        listNavigation.navigateUp()
    }
}