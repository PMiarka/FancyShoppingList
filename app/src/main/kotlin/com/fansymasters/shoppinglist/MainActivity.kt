package com.fansymasters.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.compose.FancyShoppingListTheme
import com.fansymasters.shoppinglist.list.ListsGraphBuilder
import com.fansymasters.shoppinglist.login.AuthenticationGraphBuilder
import com.fansymasters.shoppinglist.ui.NavigationRoutes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val authenticationGraphBuilder = AuthenticationGraphBuilder()
    private val listsGraphBuilder = ListsGraphBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            LaunchedEffect(true) {
                viewModel.state.collect {
                    when (it) {
                        is NavigationCommand.Route -> navController.navigate(it.route)
                        is NavigationCommand.NavigateUp -> navController.navigateUp()
                    }
                }
            }
            FancyShoppingListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = NavigationRoutes.Authentication.Login
                    ) {
                        authenticationGraphBuilder.buildGraph(this)
                        listsGraphBuilder.buildGraph(this)
                    }
                }
            }
        }
    }
}
