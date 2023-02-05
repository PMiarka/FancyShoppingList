package com.fansymasters.shoppinglist.searchuser.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fansymasters.shoppinglist.common.ProgressHandler
import com.fansymasters.shoppinglist.common.commonprocessingstate.CommonProcessingState
import com.fansymasters.shoppinglist.common.commonprocessingstate.CommonProcessingStateReader
import com.fansymasters.shoppinglist.common.commonprocessingstate.CommonProcessingStateWriter
import com.fansymasters.shoppinglist.common.handleProcessing
import com.fansymasters.shoppinglist.domain.ProcessingState
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import com.fansymasters.shoppinglist.domain.StateReader
import com.fansymasters.shoppinglist.list.details.usecase.FetchListDetailsActions
import com.fansymasters.shoppinglist.list.navigation.ListsNavigation
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
    private val commonProcessingStateWriter: CommonProcessingStateWriter,
    private val listsNavigation: ListsNavigation,
    commonProcessingStateReader: CommonProcessingStateReader,
    private val progressHandler: ProgressHandler,
    processingState: StateReader<List<UserDomainDto>>,
    savedState: SavedStateHandle,
) : ViewModel() {
    val bottomSheetState = MutableStateFlow<SetUserPermissionState>(SetUserPermissionState.Idle)
    val state = combine(
        listDetailsActions.state,
        processingState.state,
        commonProcessingStateReader.state
    ) { details, searchResult, processingState ->
        SearchUserUiState(
            apiState = processingState,
            foundUsers = searchResult,
            currentlySharedUsers = details.shopListUsers
                .filter { it.permissionType != PermissionType.OWNER.toApiKey() }
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        SearchUserUiState(CommonProcessingState.Idle, emptyList(), emptyList())
    )

    val listId =
        savedState.get<String>(NavigationRoutes.CommonArguments.LIST_ID)?.toInt() ?: -1

    init {
        handleProcessing(
            onSuccess = commonProcessingStateWriter::onSuccess,
            onError = commonProcessingStateWriter::onError
        ) {
            listDetailsActions.fetchListDetails(listId)
        }
    }

    fun startSearch(phrase: String) {
        handleProcessing(
            onSuccess = commonProcessingStateWriter::onSuccess,
            onError = commonProcessingStateWriter::onError
        ) {
            searchActions.searchUser(phrase)
        }
    }

    fun openBottomSheetPermissionTypeSelection(username: String) {
        bottomSheetState.value = SetUserPermissionState.Show(username)
    }

    fun setPermissionToUser(username: String, permissionType: PermissionType) {
        handleProcessing(
            progressHandler = progressHandler,
            onSuccess = listsNavigation::navigateUp
        ) {
            searchActions.shareListWithUser(listId, username, permissionType)
        }
    }

    fun hideBottomSheet() {
        bottomSheetState.value = SetUserPermissionState.Idle
    }
}