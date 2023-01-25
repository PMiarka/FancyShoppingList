@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.fansymasters.shoppinglist.list.overview.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fansymasters.shoppinglist.ui.components.FancyTopBar
import com.fansymasters.shoppinglist.ui.theme.Corner
import com.fansymasters.shoppinglist.ui.theme.SPACING_L
import com.fansymasters.shoppinglist.ui.theme.SPACING_S

@Composable
internal fun ListsOverviewScreen(viewModel: ListsOverviewViewModel = hiltViewModel()) {
    val state by viewModel.listsOverviewState.collectAsState()

    Content(state, viewModel)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Content(
    state: ListsOverviewState,
    viewModel: ListsOverviewViewModel
) {
    val isRefreshing by remember(state) {
        mutableStateOf(state is ListsOverviewState.Loading)
    }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = { viewModel.fetchDetails() },
    )
    Box(
        modifier = Modifier
            .pullRefresh(pullRefreshState)
            .fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                FancyTopBar(text = "My Lists")
            },
            modifier = Modifier.padding(SPACING_S.dp)
        ) { paddingValues ->

            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(SPACING_S.dp)
            ) {
                itemsIndexed(state.lists) { index, item ->
                    val rowBackground = MaterialTheme.colorScheme.secondaryContainer
                    Row(
                        modifier = Modifier
                            .clickable {
                                viewModel.openListsDetails(item.id)
                            }
                            .background(
                                shape = RoundedCornerShape(Corner.M.dp),
                                color = rowBackground
                            )
                            .fillMaxWidth()
                            .padding(SPACING_S.dp)
                    ) {
                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.contentColorFor(rowBackground),
                            modifier = Modifier.padding(horizontal = SPACING_S.dp)
                        )
                    }
                }
            }

        }
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        FloatingButton(
            onClick = viewModel::addList,
            modifier = Modifier
                .padding(SPACING_L.dp)
                .align(Alignment.BottomEnd)
        )
    }
}


@Composable
private fun FloatingButton(onClick: () -> Unit, modifier: Modifier) {
    FloatingActionButton(onClick = onClick, modifier = modifier) {
        Icon(Icons.Default.Add, contentDescription = "Add")
    }
}