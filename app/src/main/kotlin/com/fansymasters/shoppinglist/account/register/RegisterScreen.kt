package com.fansymasters.shoppinglist.account.register

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fansymasters.shoppinglist.ui.components.FancyButton
import com.fansymasters.shoppinglist.ui.components.FancyTextField
import com.fansymasters.shoppinglist.ui.components.keyboardOptionsPassword
import com.fansymasters.shoppinglist.ui.theme.SPACING_M

@Composable
internal fun RegisterScreen(viewModel: RegisterViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(SPACING_M.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val (login, setLogin) = remember { mutableStateOf("") }
        val (password, setPassword) = remember { mutableStateOf("") }
        val (name, setName) = remember { mutableStateOf("") }
        val (email, setEmail) = remember { mutableStateOf("") }
        FancyTextField(
            value = login,
            onValueChange = setLogin,
            label = "Username",
            placeholder = "Type your username"
        )
        FancyTextField(
            value = name,
            onValueChange = setName,
            label = "Name",
            placeholder = "Type your Name"
        )
        FancyTextField(
            value = password,
            onValueChange = setPassword,
            label = "Password",
            placeholder = "Type your password",
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = keyboardOptionsPassword()
        )
        FancyTextField(
            value = email,
            onValueChange = setEmail,
            label = "Email",
            placeholder = "Type your email"
        )
        Spacer(modifier = Modifier.height(40.dp))
        FancyButton(
            onClick = {
                viewModel.onRegisterClick(
                    username = login,
                    password = password,
                    name = name,
                    email = email
                )
            }
        ) {
            Text(text = "Register")
        }
    }
}