package com.fansymasters.shoppinglist.account.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.fansymasters.shoppinglist.account.register.RegisterScreen
import com.fansymasters.shoppinglist.navigation.GraphBuilder
import com.fansymasters.shoppinglist.ui.NavigationRoutes

class AuthenticationGraphBuilder : GraphBuilder {
    override fun buildGraph(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.composable(route = NavigationRoutes.Authentication.Login) {
            LoginScreen()
        }
        navGraphBuilder.composable(route = NavigationRoutes.Authentication.Register) {
            RegisterScreen()
        }
    }
}