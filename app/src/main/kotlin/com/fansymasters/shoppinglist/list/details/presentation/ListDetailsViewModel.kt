package com.fansymasters.shoppinglist.list.details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fansymasters.shoppinglist.data.room.ListItemLocalDto
import com.fansymasters.shoppinglist.domain.ProcessingState
import com.fansymasters.shoppinglist.domain.StateReader
import com.fansymasters.shoppinglist.list.details.usecase.DeleteListItemActions
import com.fansymasters.shoppinglist.list.details.usecase.FetchListDetailsActions
import com.fansymasters.shoppinglist.list.details.usecase.FetchListDetailsState
import com.fansymasters.shoppinglist.list.details.usecase.UpdateListItemActions
import com.fansymasters.shoppinglist.list.navigation.ListsNavigation
import com.fansymasters.shoppinglist.ui.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ListDetailsViewModel @Inject constructor(
    private val fetchActions: FetchListDetailsActions,
    private val updateListItemActions: UpdateListItemActions,
    private val deleteItemActions: DeleteListItemActions,
    private val fetchDetailsState: StateReader<FetchListDetailsState>,
    private val listNavigation: ListsNavigation,
    savedState: SavedStateHandle,
) : ViewModel() {

    val state = fetchDetailsState.state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        FetchListDetailsState(ProcessingState.Idle, emptyList())
    )

    val listId = savedState.get<String>(NavigationRoutes.Lists.Arguments.LIST_ID)?.toInt() ?: -1

    init {
        fetchDetails()
    }

    fun fetchDetails() {
        viewModelScope.launch {
            fetchActions.fetchListDetails(listId)
        }
    }

    fun deleteItem(listItemDto: ListItemLocalDto, finished: Boolean) {
        viewModelScope.launch {
            deleteItemActions.deleteItem(listItemDto.id)
        }
    }


    fun addItem() {
        listNavigation.openCreateItem(listId)
    }

    fun navigateUp() {
        listNavigation.navigateUp()
    }
}