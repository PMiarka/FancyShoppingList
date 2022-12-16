package com.fansymasters.shoppinglist.searchuser.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fansymasters.shoppinglist.domain.ProcessingState
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import com.fansymasters.shoppinglist.domain.StateReader
import com.fansymasters.shoppinglist.list.details.usecase.FetchListDetailsActions
import com.fansymasters.shoppinglist.list.details.usecase.FetchListDetailsState
import com.fansymasters.shoppinglist.searchuser.domain.PermissionType
import com.fansymasters.shoppinglist.searchuser.domain.UserDomainDto
import com.fansymasters.shoppinglist.searchuser.domain.toApiKey
import com.fansymasters.shoppinglist.searchuser.usecase.SearchUserActions
import com.fansymasters.shoppinglist.ui.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SearchUserViewModel @Inject constructor(
    private val searchActions: SearchUserActions,
    private val listDetailsActions: FetchListDetailsActions,
    private val processingState: ProcessingStateReader<List<UserDomainDto>>,
    fetchDetailsState: StateReader<FetchListDetailsState>,
    savedState: SavedStateHandle,
) : ViewModel() {
    val bottomSheetState = MutableStateFlow<SetUserPermissionState>(SetUserPermissionState.Idle)
    val state = fetchDetailsState.state.map {
        it.details
    }.combine(processingState.state) { details, searchResult ->
        SearchUserUiState(
            foundUsers = (searchResult as? ProcessingState.Success<List<UserDomainDto>>)?.data
                ?: emptyList(),
            currentlySharedUsers = details.shopListUsers
                .filter { it.permissionType != PermissionType.OWNER.toApiKey() }
        )
    }.stateIn(viewModelScope, SharingStarted.Lazily, SearchUserUiState(emptyList(), emptyList()))

    val listId =
        savedState.get<String>(NavigationRoutes.CommonArguments.LIST_ID)?.toInt() ?: -1

    init {
        viewModelScope.launch {
            listDetailsActions.fetchListDetails(listId)
        }
    }

    fun startSearch(phrase: String) {
        viewModelScope.launch {
            searchActions.searchUser(phrase)
        }
    }

    fun openBottomSheetPermissionTypeSelection(username: String) {
        bottomSheetState.value = SetUserPermissionState.Show(username)
    }

    fun setPermissionToUser(username: String, permissionType: PermissionType) {
        viewModelScope.launch {
            searchActions.shareListWithUser(listId, username, permissionType)
        }
    }

    fun hideBottomSheet() {
        bottomSheetState.value = SetUserPermissionState.Idle
    }
}