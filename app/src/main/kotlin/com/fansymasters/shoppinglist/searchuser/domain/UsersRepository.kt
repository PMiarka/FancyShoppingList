package com.fansymasters.shoppinglist.searchuser.domain

import com.fansymasters.shoppinglist.data.room.ShopUsersLocalDto

internal interface UsersRepository {
    suspend fun searchUsers(phrase: String): List<UserDomainDto>
    suspend fun setPermissionToUsers(listId: Int, permissions: List<ShopUsersLocalDto>)
}
