package com.fansymasters.shoppinglist.list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.fansymasters.shoppinglist.list.createlist.presentation.CreateListScreen
import com.fansymasters.shoppinglist.list.details.presentation.ListDetailsScreen
import com.fansymasters.shoppinglist.list.overview.presentation.ListsOverviewScreen
import com.fansymasters.shoppinglist.navigation.GraphBuilder
import com.fansymasters.shoppinglist.ui.NavigationRoutes
import com.fansymasters.shoppinglist.ui.getIntArgument

class ListsGraphBuilder : GraphBuilder {
    override fun buildGraph(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(
            startDestination = NavigationRoutes.Lists.Overview,
            route = NavigationRoutes.Lists.Root
        ) {
            composable(route = NavigationRoutes.Lists.Overview) {
                ListsOverviewScreen()
            }
            composable(route = NavigationRoutes.Lists.Create) {
                CreateListScreen()
            }
            composable(
                route = NavigationRoutes.Lists.Details,
                arguments = listOf(
                    navArgument(NavigationRoutes.Lists.Arguments.LIST_ID) {
                        type = NavType.StringType
                    }
                )
            ) {
                val listId = it.getIntArgument(NavigationRoutes.Lists.Arguments.LIST_ID) ?: -1
                ListDetailsScreen(listId)
            }
        }
    }
}
