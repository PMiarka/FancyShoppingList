package com.fansymasters.shoppinglist.searchuser.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.fansymasters.shoppinglist.navigation.GraphBuilder
import com.fansymasters.shoppinglist.searchuser.presentation.SearchUserScreen
import com.fansymasters.shoppinglist.ui.NavigationRoutes

internal class UsersGraphBuilder : GraphBuilder {
    override fun buildGraph(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(
            startDestination = NavigationRoutes.Users.SearchUser,
            route = NavigationRoutes.Users.Root
        ) {
            composable(
                route = NavigationRoutes.Users.SearchUser,
                arguments = listOf(navArgument(NavigationRoutes.CommonArguments.LIST_ID) {
                    type = NavType.StringType
                }
                )
            ) {
                SearchUserScreen()
            }
        }
    }
}
