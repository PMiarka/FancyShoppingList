@file:OptIn(ExperimentalMaterial3Api::class)

package com.fansymasters.shoppinglist.list.overview.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fansymasters.shoppinglist.data.lists.ListDto
import com.fansymasters.shoppinglist.domain.ProcessingState
import com.fansymasters.shoppinglist.ui.components.FancyTopBar
import com.fansymasters.shoppinglist.ui.theme.Corner
import com.fansymasters.shoppinglist.ui.theme.SPACING_S

@Composable
internal fun ListsOverviewScreen(viewModel: ListsOverviewViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState()

//    val derivedState = derivedStateOf { state.value is ProcessingState.Success }
    Content(state.value is ProcessingState.Success, state, viewModel)
}

@Composable
private fun Content(
    isShown: Boolean,
    state: State<ProcessingState<List<ListDto>>>,
    viewModel: ListsOverviewViewModel
) {

    Scaffold(
        topBar = { FancyTopBar(text = "My Lists") },
        modifier = Modifier.padding(SPACING_S.dp)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()

        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(SPACING_S.dp)
            ) {
                (state.value as? ProcessingState.Success<List<ListDto>>)?.data?.let { items ->
                    itemsIndexed(items) { index, item ->
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
            FloatingButton(
                onClick = viewModel::addList,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}


@Composable
fun FloatingButton(onClick: () -> Unit, modifier: Modifier) {
    FloatingActionButton(onClick = onClick, modifier = modifier) {
        Icon(Icons.Default.Add, contentDescription = "Add")
    }
}