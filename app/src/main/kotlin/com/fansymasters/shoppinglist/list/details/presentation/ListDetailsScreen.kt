@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)

package com.fansymasters.shoppinglist.list.details.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fansymasters.shoppinglist.domain.isProcessing
import com.fansymasters.shoppinglist.list.details.usecase.FetchListDetailsState
import com.fansymasters.shoppinglist.ui.theme.SPACING_L
import com.fansymasters.shoppinglist.ui.theme.SPACING_S

@Composable
internal fun ListDetailsScreen(
    viewModel: ListDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()

    Content(state, viewModel)
}

@Composable
private fun Content(
    state: State<FetchListDetailsState>,
    viewModel: ListDetailsViewModel
) {
    Log.e("Piotrek", "isRefreshing: ${state.value.apiState.isProcessing()}")
    val pullRefreshState =
        rememberPullRefreshState(
            refreshing = state.value.apiState.isProcessing(),
            onRefresh = { viewModel.fetchDetails() })

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .pullRefresh(pullRefreshState)
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(SPACING_S.dp)
            ) {
                state.value.items.let { items ->
                    itemsIndexed(items) { index, item ->
                        val dismissState = rememberDismissState(
                            confirmStateChange = {
                                if (it == DismissValue.DismissedToStart) {
                                    viewModel.deleteItem(item, true)
                                }
                                true
                            }
                        )
                        LaunchedEffect(key1 = item.id) {
                            dismissState.reset()
                        }
                        SwipeToDismiss(
                            state = dismissState,
                            directions = setOf(DismissDirection.EndToStart),
                            background = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.Red)
                                )
                            }) {
                            Row(
                                modifier = Modifier
                                    .background(
                                        if (index % 2 == 0) {
                                            MaterialTheme.colorScheme.primaryContainer
                                        } else {
                                            MaterialTheme.colorScheme.secondaryContainer
                                        }
                                    )
                                    .fillMaxWidth()
                                    .padding(SPACING_S.dp)
                            ) {
                                Text(text = item.name, modifier = Modifier)
                                Text(text = item.category, modifier = Modifier)
                            }
                        }
                    }
                }
            }
            PullRefreshIndicator(
                refreshing = state.value.apiState.isProcessing(),
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
            FloatingButton(
                onClick = viewModel::addItem,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(SPACING_L.dp)
            )
        }
    }
}


@Composable
private fun FloatingButton(onClick: () -> Unit, modifier: Modifier) {
    FloatingActionButton(onClick = onClick, modifier = modifier) {
        Icon(Icons.Default.Add, contentDescription = "Add")
    }
}