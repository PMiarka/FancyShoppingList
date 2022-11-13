@file:OptIn(ExperimentalMaterial3Api::class)

package com.fansymasters.shoppinglist.list.createitem.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fansymasters.shoppinglist.ui.components.FancyTextField
import com.fansymasters.shoppinglist.ui.theme.SPACING_M

@Composable
internal fun CreateItemScreen(viewModel: CreateItemViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState()
    val nameState = remember { mutableStateOf("") }

    Scaffold(topBar = {
        TopbarTextField(name = nameState.value,
            onValueChange = { nameState.value = it },
            onDone = { viewModel.createItem(name = nameState.value) })
    }) {
        Column(
            verticalArrangement = Arrangement.spacedBy(SPACING_M.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            FloatingButton(
                onClick = { viewModel.createItem(name = nameState.value) }, modifier = Modifier
            )
        }
    }
}

@Composable
private fun TopbarTextField(name: String, onValueChange: (String) -> Unit, onDone: () -> Unit) {
    FancyTextField(
        value = name,
        onValueChange = onValueChange,
        label = "Name",
        placeholder = "New Item",
        keyboardActions = KeyboardActions(onDone = { onDone() }),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun FloatingButton(onClick: () -> Unit, modifier: Modifier) {
    FloatingActionButton(onClick = onClick, modifier = modifier) {
        Icon(Icons.Default.Check, contentDescription = "Confirm")
    }
}