package com.fansymasters.shoppinglist.searchuser

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import com.fansymasters.shoppinglist.searchuser.domain.PermissionType
import com.fansymasters.shoppinglist.searchuser.domain.UserDomainDto
import com.fansymasters.shoppinglist.searchuser.usecase.SearchUserActions
import com.fansymasters.shoppinglist.ui.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SearchUserViewModel @Inject constructor(
    private val actions: SearchUserActions,
    private val processingState: ProcessingStateReader<List<UserDomainDto>>,
    savedState: SavedStateHandle,
) : ViewModel(),
    ProcessingStateReader<List<UserDomainDto>> by processingState {
    val bottomSheetState = MutableStateFlow<SetUserPermissionState>(SetUserPermissionState.Idle)

    val listId =
        savedState.get<String>(NavigationRoutes.CommonArguments.LIST_ID)?.toInt() ?: -1

    fun startSearch(phrase: String) {
        viewModelScope.launch {
            actions.searchUser(phrase)
        }
    }

    fun openBottomSheetPermissionTypeSelection(username: String) {
        bottomSheetState.value = SetUserPermissionState.Show(username)
    }

    fun setPermissionToUser(username: String, permissionType: PermissionType) {
        viewModelScope.launch {
            actions.shareListWithUser(listId, username, permissionType)
        }
    }

    fun hideBottomSheet() {
        bottomSheetState.value = SetUserPermissionState.Idle
    }
}