@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)

package com.fansymasters.shoppinglist.searchuser

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fansymasters.shoppinglist.domain.ProcessingState
import com.fansymasters.shoppinglist.searchuser.domain.PermissionType
import com.fansymasters.shoppinglist.searchuser.domain.UserDomainDto
import com.fansymasters.shoppinglist.searchuser.domain.toDisplayText
import com.fansymasters.shoppinglist.ui.components.FancyButton
import com.fansymasters.shoppinglist.ui.components.FancyTextField
import com.fansymasters.shoppinglist.ui.theme.SPACING_M
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow

@Composable
internal fun SearchUserScreen(viewModel: SearchUserViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState()
    val bottomSheetState = viewModel.bottomSheetState.collectAsState()
    Content(
        state = (state.value as? ProcessingState.Success)?.data ?: listOf(),
        bottomSheetState = bottomSheetState.value,
        viewModel = viewModel
    )
}

@Composable
private fun Content(
    state: List<UserDomainDto>,
    bottomSheetState: SetUserPermissionState,
    viewModel: SearchUserViewModel
) {
    var textState by remember { mutableStateOf("") }
    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    LaunchedEffect(bottomSheetState) {
        if (bottomSheetState is SetUserPermissionState.Show) {
            modalBottomSheetState.show()
        } else {
            modalBottomSheetState.hide()
        }
    }
    ModalBottomSheetLayout(
        sheetContent = {
            val username = remember { mutableStateOf("") }
            val selectedPermission = remember { mutableStateOf(PermissionType.READ_ONLY) }
            if (bottomSheetState is SetUserPermissionState.Show) {
                username.value = bottomSheetState.username
            }
            Text(
                text = username.value,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(SPACING_M.dp)
                    .align(CenterHorizontally)
            )
            FlowRow(
                mainAxisAlignment = FlowMainAxisAlignment.Center,
                mainAxisSpacing = SPACING_M.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally),
            ) {
                PermissionType.values().forEach { permissionType ->
                    FilterChip(
                        selected = selectedPermission.value == permissionType,
                        onClick = { selectedPermission.value = permissionType },
                        label = {
                            Text(
                                text = permissionType.toDisplayText(),
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.wrapContentWidth()
                            )
                        })
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
            ) {
                FancyButton(onClick = {
                    viewModel.hideBottomSheet()
                }) {
                    Text(
                        text = "Cancel",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.wrapContentWidth()
                    )
                }
                FancyButton(onClick = {
                    viewModel.setPermissionToUser(
                        username.value,
                        selectedPermission.value
                    )
                    viewModel.hideBottomSheet()
                }) {
                    Text(
                        text = "Confirm",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.wrapContentWidth()
                    )
                }
            }
        },
        sheetState = modalBottomSheetState,
        sheetBackgroundColor = MaterialTheme.colorScheme.surface
    ) {
        Column(modifier = Modifier.padding(SPACING_M.dp)) {
            FancyTextField(
                value = textState,
                onValueChange = {
                    textState = it
                    viewModel.startSearch(it)
                },
                label = "Type to Search",
                placeholder = "username",
                modifier = Modifier.fillMaxWidth(),
            )
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state) {
                    Text(
                        text = it.username,
                        modifier = Modifier
                            .padding(SPACING_M.dp)
                            .clickable { viewModel.openBottomSheetPermissionTypeSelection(it.username) }
                    )
                }
            }
        }
    }
}


@Composable
fun FloatingButton(onClick: () -> Unit, modifier: Modifier) {
    FloatingActionButton(onClick = onClick, modifier = modifier) {
        Icon(Icons.Default.Add, contentDescription = "Add")
    }
}