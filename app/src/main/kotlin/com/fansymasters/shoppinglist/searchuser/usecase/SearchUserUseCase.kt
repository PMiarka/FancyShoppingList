package com.fansymasters.shoppinglist.searchuser.usecase

import com.fansymasters.shoppinglist.data.onError
import com.fansymasters.shoppinglist.data.onSuccess
import com.fansymasters.shoppinglist.data.room.ShopUsersLocalDto
import com.fansymasters.shoppinglist.domain.ProcessingState
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import com.fansymasters.shoppinglist.searchuser.domain.PermissionType
import com.fansymasters.shoppinglist.searchuser.domain.UserDomainDto
import com.fansymasters.shoppinglist.searchuser.domain.UsersRepository
import com.fansymasters.shoppinglist.searchuser.domain.toApiKey
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@ViewModelScoped
internal class SearchUserUseCase @Inject constructor(private val repository: UsersRepository) :
    ProcessingStateReader<List<UserDomainDto>>, SearchUserActions {
    override val state =
        MutableStateFlow<ProcessingState<List<UserDomainDto>>>(ProcessingState.Idle)

    override suspend fun searchUser(phrase: String) {
        state.value = ProcessingState.Processing
        repository.searchUsers(phrase)
            .onSuccess { state.value = ProcessingState.Success(it) }
            .onError { state.value = ProcessingState.Error(it) }
    }

    override suspend fun shareListWithUser(
        listId: Int,
        username: String,
        permissionType: PermissionType
    ) {
        repository.setPermissionToUsers(
            listId,
            listOf(
                ShopUsersLocalDto(
                    username = username,
                    permissionType = permissionType.toApiKey()
                )
            )
        )
    }
}