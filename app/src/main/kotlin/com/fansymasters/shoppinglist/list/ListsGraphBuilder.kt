package com.fansymasters.shoppinglist.list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.fansymasters.shoppinglist.createlist.CreateListScreen
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
        }
    }
}