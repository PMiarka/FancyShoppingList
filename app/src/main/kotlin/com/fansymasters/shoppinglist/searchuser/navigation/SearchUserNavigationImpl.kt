package com.fansymasters.shoppinglist.searchuser.navigation

import com.fansymasters.shoppinglist.NavigationWriter
import com.fansymasters.shoppinglist.ui.NavigationRoutes
import com.fansymasters.shoppinglist.ui.setIntArgument
import javax.inject.Inject

internal class SearchUserNavigationImpl @Inject constructor(
    private val navigationWriter: NavigationWriter,
) : SearchUserNavigation {
    override fun openSearchUser(listId: Int) {
        navigationWriter.navigate(
            NavigationRoutes.Users.SearchUser.setIntArgument(
                key = NavigationRoutes.CommonArguments.LIST_ID,
                value = listId
            )
        )
    }
}