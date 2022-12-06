package com.fansymasters.shoppinglist.searchuser.data

import com.fansymasters.shoppinglist.common.Mapper
import com.fansymasters.shoppinglist.common.unitMapper
import com.fansymasters.shoppinglist.data.ApiResult
import com.fansymasters.shoppinglist.data.apiCall
import com.fansymasters.shoppinglist.data.lists.ListsApi
import com.fansymasters.shoppinglist.data.lists.ShopUsersDto
import com.fansymasters.shoppinglist.data.room.ShopUsersLocalDto
import com.fansymasters.shoppinglist.data.user.UsersApi
import com.fansymasters.shoppinglist.searchuser.domain.UserDomainDto
import com.fansymasters.shoppinglist.searchuser.domain.UsersRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class UsersRepositoryImpl @Inject constructor(
    private val usersApi: UsersApi,
    private val listsApi: ListsApi,
    private val usersMapper: Mapper<String, UserDomainDto>,
    private val shopUsersMapper: Mapper<ShopUsersLocalDto, ShopUsersDto>
) : UsersRepository {
    override suspend fun searchUsers(phrase: String): ApiResult<List<UserDomainDto>> =
        apiCall(
            mapper = { it.map(usersMapper::map) },
            function = { usersApi.searchPhrase(phrase) }
        )

    override suspend fun setPermissionToUsers(
        listId: Int,
        permissions: List<ShopUsersLocalDto>
    ): ApiResult<Unit> =
        apiCall(
            mapper = unitMapper(),
            function = {
                listsApi.setUsersPermissions(
                    listId,
                    permissions.map(shopUsersMapper::map)
                )
            }
        )
}
