@file:OptIn(ExperimentalMaterial3Api::class)

package com.fansymasters.shoppinglist.list.createitem.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fansymasters.shoppinglist.data.lists.di.Category
import com.fansymasters.shoppinglist.ui.components.FancyTextField
import com.fansymasters.shoppinglist.ui.theme.SPACING_L
import com.fansymasters.shoppinglist.ui.theme.SPACING_M

@Composable
internal fun CreateItemScreen(viewModel: CreateItemViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState()
    val nameState = remember { mutableStateOf("") }
    val unitState = remember { mutableStateOf("") }
    val quantityState = remember { mutableStateOf("") }
    val isExpanded = remember { mutableStateOf(false) }
    val selectedCategory = remember { mutableStateOf(Category.OTHER) }

    Scaffold(topBar = {
        TopbarTextField(name = nameState.value, onValueChange = { nameState.value = it }, onDone = {
            viewModel.createItem(
                name = nameState.value,
                unit = unitState.value,
                quantity = quantityState.value.toIntOrNull() ?: 1,
                category = selectedCategory.value
            )
        })
    }) {
        Column(
            verticalArrangement = Arrangement.spacedBy(SPACING_M.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(horizontal = SPACING_M.dp)
        ) {
            Row {
                FancyTextField(
                    value = quantityState.value,
                    onValueChange = { value ->
                        quantityState.value = value.filter { it.isDigit() }
                    },
                    label = "quantity",
                    placeholder = "",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.weight(1f)
                )
                FancyTextField(
                    value = unitState.value,
                    onValueChange = { unitState.value = it },
                    label = "Unit",
                    placeholder = "kg",
                    modifier = Modifier.weight(1f)
                )
            }
            Box(contentAlignment = Alignment.TopStart, modifier = Modifier.fillMaxWidth()) {
                Text(text = selectedCategory.value.name,
                    modifier = Modifier.clickable { isExpanded.value = true })
                DropdownMenu(expanded = isExpanded.value,
                    onDismissRequest = { isExpanded.value = false }) {
                    Category.values().forEachIndexed { index, category ->
                        DropdownMenuItem(text = { Text(text = category.name) }, onClick = {
                            selectedCategory.value = Category.values()[index]
                            isExpanded.value = false
                        })
                    }
                }
            }
            Box(modifier = Modifier.fillMaxSize()) {
                FloatingButton(
                    onClick = {
                        viewModel.createItem(
                            name = nameState.value,
                            unit = unitState.value,
                            quantity = quantityState.value.toIntOrNull() ?: 1,
                            category = selectedCategory.value
                        )
                    }, modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(SPACING_L.dp)
                )
            }
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(SPACING_M.dp)
    )
}

@Composable
private fun FloatingButton(onClick: () -> Unit, modifier: Modifier) {
    FloatingActionButton(onClick = onClick, modifier = modifier) {
        Icon(Icons.Default.Check, contentDescription = "Confirm")
    }
}