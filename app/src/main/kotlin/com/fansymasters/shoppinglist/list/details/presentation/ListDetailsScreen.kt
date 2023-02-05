@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)

package com.fansymasters.shoppinglist.list.details.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fansymasters.shoppinglist.common.commonprocessingstate.CommonProcessingState
import com.fansymasters.shoppinglist.data.room.ListItemLocalDto
import com.fansymasters.shoppinglist.list.details.usecase.FetchListDetailsState
import com.fansymasters.shoppinglist.ui.components.FancyTopBar
import com.fansymasters.shoppinglist.ui.theme.SPACING_L
import com.fansymasters.shoppinglist.ui.theme.SPACING_S
import com.fansymasters.shoppinglist.ui.theme.WEIGHT_ONE

@Composable
internal fun ListDetailsScreen(
    viewModel: ListDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()

    Content(
        state = state.value,
        onRefresh = viewModel::fetchDetails,
        openSearchUser = viewModel::openSearchUsers,
        deleteItem = viewModel::deleteItem,
        setItemFinished = viewModel::setItemFinished,
        addItem = viewModel::addItem
    )
}

@Composable
private fun Content(
    state: FetchListDetailsState,
    onRefresh: () -> Unit,
    openSearchUser: (id: Int) -> Unit,
    deleteItem: (ListItemLocalDto) -> Unit,
    setItemFinished: (ListItemLocalDto) -> Unit,
    addItem: () -> Unit

) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.apiState is CommonProcessingState.Processing,
        onRefresh = onRefresh,
    )

    Box(
        modifier = Modifier
            .pullRefresh(pullRefreshState)
            .fillMaxSize()
    ) {
        Column {
            FancyTopBar(
                text = state.details.name,
                trailingIcons = {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "",
                        modifier = Modifier.clickable { openSearchUser(state.details.id) }
                    )
                },
                modifier = Modifier.background(MaterialTheme.colorScheme.surfaceVariant)
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(
                    items = state.details.shopListItems,
                    key = { _, item -> item.id },
                    itemContent = { index, item ->
                        ListItemContent(
                            item = item,
                            isErrorState = state.apiState is CommonProcessingState.Error,
                            deleteItem = deleteItem,
                            setItemFinished = setItemFinished
                        )
                        Divider(
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.12f)
                        ).takeIf { index != state.details.shopListItems.size }
                    }
                )
            }
        }
        PullRefreshIndicator(
            refreshing = state.apiState is CommonProcessingState.Processing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        FloatingButton(
            onClick = addItem,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(SPACING_L.dp)
                .background(
                    shape = FloatingActionButtonDefaults.shape,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
        )
    }
}

@Composable
private fun ListItemContent(
    item: ListItemLocalDto,
    isErrorState: Boolean,
    deleteItem: (ListItemLocalDto) -> Unit,
    setItemFinished: (ListItemLocalDto) -> Unit
) {
    val dismissState = rememberDismissState(
        confirmStateChange = {
            if (it == DismissValue.DismissedToStart) {
                deleteItem(item)
            }
            true
        })
    val isAboutToDelete by remember(dismissState) {
        derivedStateOf {
            dismissState.isDismissed(DismissDirection.EndToStart)
        }
    }
    LaunchedEffect(
        key1 = item.id,
        key2 = isErrorState,
        key3 = isAboutToDelete
    ) {
        if (isAboutToDelete) {
            dismissState.reset()
        }
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
        val rowColor = if (item.finished) {
            MaterialTheme.colorScheme.background
        } else {
            MaterialTheme.colorScheme.surfaceVariant
        }
        Row(modifier = Modifier
            .clickable { setItemFinished(item) }
            .background(rowColor)
            .fillMaxWidth()
            .padding(horizontal = SPACING_L.dp, vertical = SPACING_S.dp)) {
            val textColor = if (item.finished) {
                MaterialTheme.colorScheme.outline
            } else {
                MaterialTheme.colorScheme.onBackground
            }
            val iconColor = if (item.finished) {
                MaterialTheme.colorScheme.tertiary
            } else {
                MaterialTheme.colorScheme.secondary
            }

            Icon(
                imageVector = Icons.Default.CheckCircle,
                tint = iconColor,
                contentDescription = "",
                modifier = Modifier.padding(end = SPACING_S.dp)
            )
            Column {
                Row {
                    Text(
                        text = item.name,
                        color = textColor,
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.weight(WEIGHT_ONE))
                    Text(
                        text = item.qty.toString(),
                        color = textColor,
                        modifier = Modifier
                    )
                }
//                Text(
//                    text = item.category,
//                    color = textColor,
//                    modifier = Modifier
//                )
            }
        }
    }
}

@Composable
private fun FloatingButton(onClick: () -> Unit, modifier: Modifier) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add"
        )
    }
}