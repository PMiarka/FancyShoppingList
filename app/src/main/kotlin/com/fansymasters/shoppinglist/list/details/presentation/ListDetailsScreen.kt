@file:OptIn(ExperimentalMaterial3Api::class)

package com.fansymasters.shoppinglist.list.details.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.fansymasters.shoppinglist.data.lists.ListDetailsDto
import com.fansymasters.shoppinglist.domain.ProcessingState
import com.fansymasters.shoppinglist.ui.components.FancyTopBar
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
    state: State<ProcessingState<ListDetailsDto>>,
    viewModel: ListDetailsViewModel
) {
    val successState = (state.value as? ProcessingState.Success<ListDetailsDto>)
    Scaffold(
        topBar = { FancyTopBar(successState?.data?.name ?: "", null) },
        modifier = Modifier.padding(vertical = SPACING_L.dp)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(SPACING_S.dp)
            ) {
                successState?.data?.shopListItems?.let { items ->
                    itemsIndexed(items) { index, item ->
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
            FloatingButton(
                onClick = viewModel::addItem,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
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