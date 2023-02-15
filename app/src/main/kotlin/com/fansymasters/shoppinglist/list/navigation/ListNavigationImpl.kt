package com.fansymasters.shoppinglist.list.navigation

import com.fansymasters.shoppinglist.NavigationWriter
import com.fansymasters.shoppinglist.ui.NavigationRoutes
import com.fansymasters.shoppinglist.ui.setIntArgument
import javax.inject.Inject

internal class ListNavigationImpl @Inject constructor(
    private val navigationWriter: NavigationWriter,
) : ListsNavigation {
    override fun openCreateList() {
        navigationWriter.navigate(NavigationRoutes.Lists.Create)
    }

    override fun openListsOverview() {
        navigationWriter.navigate(NavigationRoutes.Lists.Overview)
    }

    override fun openListDetails(listId: Int) {
        val route = NavigationRoutes.Lists.Details.setIntArgument(
            key = NavigationRoutes.CommonArguments.LIST_ID,
            value = listId
        )
        navigationWriter.navigate(route)
    }

    override fun openCreateOrUpdateItem(listId: Int, itemId: Int?) {
        val route = NavigationRoutes.Lists.CreateItem
            .setIntArgument(
                key = NavigationRoutes.CommonArguments.LIST_ID,
                value = listId
            )
            .setIntArgument(
                key = NavigationRoutes.CommonArguments.ITEM_ID,
                value  = itemId?: NavigationRoutes.CommonArguments.ITEM_ID_UNDEFINED
            )
        navigationWriter.navigate(route)
    }

    override fun navigateUp() {
        navigationWriter.navigateUp()
    }
}