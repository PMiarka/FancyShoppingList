@file:OptIn(ExperimentalMaterial3Api::class)

package com.fansymasters.shoppinglist.list.createitem.presentation

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fansymasters.shoppinglist.data.lists.Category
import com.fansymasters.shoppinglist.data.lists.toDisplayText
import com.fansymasters.shoppinglist.ui.components.FancyTextField
import com.fansymasters.shoppinglist.ui.theme.SPACING_L
import com.fansymasters.shoppinglist.ui.theme.SPACING_M
import com.fansymasters.shoppinglist.ui.theme.SPACING_S
import com.google.accompanist.flowlayout.FlowRow

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun CreateItemScreen(viewModel: CreateItemViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState()
    var isAdvancedItemCreation by remember { mutableStateOf(false) }

    Scaffold(topBar = {

        TopbarTextField(
            name = state.value.item.name,
            onValueChange = viewModel::onNameChanged,
            onDone = viewModel::createItem,
            onExpandClick = { isAdvancedItemCreation = !isAdvancedItemCreation },
            onClearClick = { viewModel.onNameChanged("") },
            onAddClick = {},
            onBackClick = viewModel::back
        )
    }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(SPACING_M.dp),
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(horizontal = SPACING_M.dp)
        ) {
            AnimatedVisibility(visible = isAdvancedItemCreation) {
                Column {
                    Row {
                        FancyTextField(
                            value = state.value.item.qty.takeIf { it > 0 }?.toString() ?: "",
                            onValueChange = { value ->
                                Log.e("Piotrek", "currentvalue: $value")
                                viewModel.onQuantityChanged(
                                    value.filter { it.isDigit() }
                                        .ifEmpty { "1" }
                                        .toInt()
                                )
                            },
                            label = "quantity",
                            placeholder = "",
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(SPACING_M.dp))
                        FancyTextField(
                            value = state.value.item.unit,
                            onValueChange = viewModel::onUnitChanged,
                            label = "Unit",
                            placeholder = "kg",
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(modifier = Modifier.height(SPACING_S.dp))
                    Text(
                        text = "Category",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = SPACING_M.dp)
                    )
                    FlowRow(
                        mainAxisSpacing = SPACING_M.dp,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Category.values().forEach { item ->
                            FilterChip(
                                selected = item == state.value.item.category,
                                onClick = { viewModel.onCategoryChanged(item) },
                                label = {
                                    Text(
                                        text = item.toDisplayText(),
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.wrapContentWidth()
                                    )
                                })
                        }
                    }
                }
            }
            Box(modifier = Modifier.fillMaxSize()) {
                FloatingButton(
                    onClick = viewModel::createItem,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(SPACING_L.dp)
                )
            }
        }
    }
}

@Composable
private fun TopbarTextField(
    name: String,
    onValueChange: (String) -> Unit,
    onDone: () -> Unit,
    onExpandClick: () -> Unit,
    onClearClick: () -> Unit,
    onAddClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = SPACING_M.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .padding(SPACING_S.dp)
                .clickable(onClick = onBackClick)
        )

        FancyTextField(
            value = name,
            onValueChange = onValueChange,
            label = "Name",
            placeholder = "New Item",
            keyboardActions = KeyboardActions(onDone = { onDone() }),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "clear",
                    modifier = Modifier.clickable(onClick = onClearClick)
                )
            },
            modifier = Modifier
                .weight(1f)
                .padding(vertical = SPACING_M.dp)
        )
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "expand",
            modifier = Modifier
                .clip(CircleShape)
                .clickable(onClick = onExpandClick)
                .padding(SPACING_M.dp)
        )
    }
}

@Composable
private fun FloatingButton(onClick: () -> Unit, modifier: Modifier) {
    FloatingActionButton(onClick = onClick, modifier = modifier) {
        Icon(Icons.Default.Check, contentDescription = "Confirm")
    }
}