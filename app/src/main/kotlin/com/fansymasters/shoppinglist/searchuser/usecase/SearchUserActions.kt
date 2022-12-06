package com.fansymasters.shoppinglist.searchuser.usecase

import com.fansymasters.shoppinglist.searchuser.domain.PermissionType

internal interface SearchUserActions {
    suspend fun searchUser(phrase: String)
    suspend fun shareListWithUser(listId: Int, username: String, permissionType: PermissionType)
}
