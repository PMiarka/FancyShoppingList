package com.fansymasters.shoppinglist.list.details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fansymasters.shoppinglist.data.room.ListDetailsLocalDto
import com.fansymasters.shoppinglist.data.room.ListItemLocalDto
import com.fansymasters.shoppinglist.domain.ProcessingState
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import com.fansymasters.shoppinglist.domain.StateReader
import com.fansymasters.shoppinglist.list.createitem.di.DeleteItem
import com.fansymasters.shoppinglist.list.details.usecase.DeleteListItemActions
import com.fansymasters.shoppinglist.list.details.usecase.FetchListDetailsActions
import com.fansymasters.shoppinglist.list.details.usecase.FetchListDetailsState
import com.fansymasters.shoppinglist.list.details.usecase.UpdateListItemActions
import com.fansymasters.shoppinglist.list.navigation.ListsNavigation
import com.fansymasters.shoppinglist.searchuser.navigation.SearchUserNavigation
import com.fansymasters.shoppinglist.ui.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ListDetailsViewModel @Inject constructor(
    private val fetchActions: FetchListDetailsActions,
    private val updateListItemActions: UpdateListItemActions,
    private val deleteItemActions: DeleteListItemActions,
    private val listNavigation: ListsNavigation,
    private val usersNavigation: SearchUserNavigation,
    @DeleteItem deleteStateReader: ProcessingStateReader<Unit>,
    fetchDetailsState: StateReader<FetchListDetailsState>,
    updateListItemState: ProcessingStateReader<ListItemLocalDto>,
    savedState: SavedStateHandle,
) : ViewModel() {

    val state = fetchDetailsState.state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        FetchListDetailsState(
            ProcessingState.Idle,
            ListDetailsLocalDto("", 0, "", emptyList(), emptyList())
        )
    )

    val listId =
        savedState.get<String>(NavigationRoutes.CommonArguments.LIST_ID)?.toInt() ?: -1

    init {
        fetchDetails()
        updateListItemState.state
            .filterIsInstance<ProcessingState.Success<ListItemLocalDto>>()
            .onEach { fetchDetails() }
            .launchIn(viewModelScope)
        deleteStateReader.state
            .filterIsInstance<ProcessingState.Success<Unit>>()
            .onEach { fetchDetails() }
            .launchIn(viewModelScope)
    }

    fun fetchDetails() {
        viewModelScope.launch {
            fetchActions.fetchListDetails(listId)
        }
    }

    fun setItemFinished(listItemDto: ListItemLocalDto) {
        viewModelScope.launch {
            updateListItemActions.setItemFinished(listItemDto)
        }
    }

    fun deleteItem(listItemDto: ListItemLocalDto) {
        viewModelScope.launch {
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