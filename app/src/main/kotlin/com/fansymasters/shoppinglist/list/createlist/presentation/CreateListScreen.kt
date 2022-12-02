@file:OptIn(ExperimentalMaterial3Api::class)

package com.fansymasters.shoppinglist.list.createlist.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fansymasters.shoppinglist.ui.components.FancyButton
import com.fansymasters.shoppinglist.ui.components.FancyTextField
import com.fansymasters.shoppinglist.ui.components.FancyTopBar
import com.fansymasters.shoppinglist.ui.theme.SPACING_M

@Composable
internal fun CreateListScreen(viewModel: CreateListViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState()
    val nameState = remember { mutableStateOf("") }
    val descriptionState = remember { mutableStateOf("") }
    Scaffold(
        topBar = { FancyTopBar("Create List", onBackClick = viewModel::navigateUp) }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(SPACING_M.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
        ) {
            FancyTextField(
                value = nameState.value,
                onValueChange = { nameState.value = it },
                label = "Name",
                placeholder = "My List"
            )
            FancyTextField(
                value = descriptionState.value,
                onValueChange = { descriptionState.value = it },
                label = "Description",
                placeholder = "This is my grocery list"
            )
            FancyButton(
                onClick = {
                    viewModel.createList(
                        name = nameState.value,
                        description = descriptionState.value
                    )
                }) {
                Text(text = "Create")
            }
        }
    }
}
