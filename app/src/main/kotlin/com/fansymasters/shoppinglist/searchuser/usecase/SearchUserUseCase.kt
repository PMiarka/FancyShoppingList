package com.fansymasters.shoppinglist.searchuser.usecase

import com.fansymasters.shoppinglist.data.room.ShopUsersLocalDto
import com.fansymasters.shoppinglist.domain.ProcessingState
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import com.fansymasters.shoppinglist.domain.StateReader
import com.fansymasters.shoppinglist.searchuser.domain.PermissionType
import com.fansymasters.shoppinglist.searchuser.domain.UserDomainDto
import com.fansymasters.shoppinglist.searchuser.domain.UsersRepository
import com.fansymasters.shoppinglist.searchuser.domain.toApiKey
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@ViewModelScoped
internal class SearchUserUseCase @Inject constructor(private val repository: UsersRepository) :
    SearchUserActions, StateReader<List<UserDomainDto>> {
    override val state = MutableStateFlow<List<UserDomainDto>>(emptyList())

    override suspend fun searchUser(phrase: String) {
        state.value = repository.searchUsers(phrase)
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