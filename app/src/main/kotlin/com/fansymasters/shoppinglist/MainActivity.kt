package com.fansymasters.shoppinglist

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.fansymasters.shoppinglist.account.login.AuthenticationGraphBuilder
import com.fansymasters.shoppinglist.list.ListsGraphBuilder
import com.fansymasters.shoppinglist.presentation.UiEvent
import com.fansymasters.shoppinglist.searchuser.navigation.UsersGraphBuilder
import com.fansymasters.shoppinglist.ui.NavigationRoutes
import com.fansymasters.shoppinglist.ui.theme.FancyShoppingListTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

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
            LaunchedEffect(true) {
                viewModel.generaleErrorState
                    .onEach { Log.e("Piotrek", "error") }
                    .collect {
                        Log.e("Piotrek", "general error collected: $it")
                        Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                    }
            }


            val progressState = viewModel.progressState.collectAsState(initial = false)

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
                    if (progressState.value) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }
                    }
                }
            }
        }
    }
}
