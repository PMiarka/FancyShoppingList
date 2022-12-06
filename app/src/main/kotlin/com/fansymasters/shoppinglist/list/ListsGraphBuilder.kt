package com.fansymasters.shoppinglist.list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.fansymasters.shoppinglist.list.createitem.presentation.CreateItemScreen
import com.fansymasters.shoppinglist.list.createlist.presentation.CreateListScreen
import com.fansymasters.shoppinglist.list.details.presentation.ListDetailsScreen
import com.fansymasters.shoppinglist.list.overview.presentation.ListsOverviewScreen
import com.fansymasters.shoppinglist.navigation.GraphBuilder
import com.fansymasters.shoppinglist.ui.NavigationRoutes

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
                    navArgument(NavigationRoutes.CommonArguments.LIST_ID) {
                        type = NavType.StringType
                    }
                )
            ) {
                ListDetailsScreen()
            }
            composable(
                route = NavigationRoutes.Lists.CreateItem,
                arguments = listOf(
                    navArgument(NavigationRoutes.CommonArguments.LIST_ID) {
                        type = NavType.StringType
                    }
                )
            ) {
                CreateItemScreen()
            }
        }
    }
}

