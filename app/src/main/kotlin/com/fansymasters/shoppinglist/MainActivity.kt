package com.fansymasters.shoppinglist

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.fansymasters.shoppinglist.list.ListsGraphBuilder
import com.fansymasters.shoppinglist.login.AuthenticationGraphBuilder
import com.fansymasters.shoppinglist.presentation.UiEvent
import com.fansymasters.shoppinglist.searchuser.navigation.UsersGraphBuilder
import com.fansymasters.shoppinglist.ui.NavigationRoutes
import com.fansymasters.shoppinglist.ui.theme.FancyShoppingListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val authenticationGraphBuilder = AuthenticationGraphBuilder()
    private val listsGraphBuilder = ListsGraphBuilder()
    private val usersGraphBuilder = UsersGraphBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            LaunchedEffect(true) {
                viewModel.navigationState.collect {
                    when (it) {
                        is NavigationCommand.Route -> navController.navigate(it.route)
                        is NavigationCommand.NavigateUp -> navController.navigateUp()
                    }
                }
            }
            val context = LocalContext.current
            LaunchedEffect(true) {
                viewModel.uiEventState.collect {
                    Log.e("Piotrek", "uiEvent state collect: $it")
                    when (it) {
                        is UiEvent.Idle -> Unit
                        is UiEvent.ShowToast -> Toast.makeText(
                            context,
                            it.message,
                            Toast.LENGTH_SHORT
                        ).show()
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
                        usersGraphBuilder.buildGraph(this)
                    }
                }
            }
        }
    }
}
