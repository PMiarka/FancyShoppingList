package com.fansymasters.shoppinglist.account.login

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fansymasters.shoppinglist.ui.components.FancyButton
import com.fansymasters.shoppinglist.ui.components.FancyClickableText
import com.fansymasters.shoppinglist.ui.components.FancyTextField
import com.fansymasters.shoppinglist.ui.components.keyboardOptionsPassword
import com.fansymasters.shoppinglist.ui.theme.SPACING_M
import com.fansymasters.shoppinglist.ui.theme.SPACING_S
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.launch

@Composable
internal fun LoginScreen(viewModel: LoginViewModel = hiltViewModel()) {

    val state = viewModel.progressState.collectAsState(false)

    var text = remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val signInRequestCode = 200

    val isUserRegistered = GoogleSignIn.getLastSignedInAccount(LocalContext.current)

    val authResultLauncher =
        rememberLauncherForActivityResult(contract = AuthResultContract()) { task ->
            try {
                val account = task?.getResult(ApiException::class.java)
                if (account == null) {
                    text.value = "Google sign in failed"
                } else {
                    coroutineScope.launch {
                        viewModel.onRegisterUsingGoogleAccountClick(account.id ?: "")
                    }
                }
            } catch (e: ApiException) {
                Log.e("authResultLauncher", "Google Error, message: ${e.message}")
                Log.e("authResultLauncher", "Google Error, cause: ${e.cause}")
                Log.e(
                    "authResultLauncher", "Google Error, status code: ${e.statusCode}"
                )
                Log.d("authResultLauncher", "Google Error: ${e}")

                text.value = "Google sign in failed"
            }
        }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(SPACING_M.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val (login, setLogin) = remember { mutableStateOf("") }
        val (password, setPassword) = remember { mutableStateOf("") }
        val (keepLoggedIn, setKeepLoggedIn) = remember { mutableStateOf(false) }
        FancyTextField(
            value = login,
            onValueChange = setLogin,
            label = "Username",
            placeholder = "Type your username",
            modifier = Modifier
                .fillMaxWidth()
                .padding(SPACING_S.dp)
        )
        FancyTextField(
            value = password,
            onValueChange = setPassword,
            label = "Passwrod",
            placeholder = "Type your passwrod",
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = keyboardOptionsPassword(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(SPACING_S.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(checked = keepLoggedIn, onCheckedChange = setKeepLoggedIn)
            Text(text = "Keep me logged in")
        }
        Spacer(modifier = Modifier.height(40.dp))
        FancyButton(onClick = {
            viewModel.onLogInClick(
                username = login,
                password = password,
                keepLoggedIn = keepLoggedIn
            )
        }) {
            Text(text = "Log in")
        }
        Spacer(modifier = Modifier.height(SPACING_S.dp))
        FancyButton(onClick = { authResultLauncher.launch(signInRequestCode) }) {
            Text(text = "Log in with google")
        }
        Spacer(modifier = Modifier.height(SPACING_M.dp))
        FancyClickableText(
            text = "Don't have an account? Sign in here",
            onClick = viewModel::onSignInClick,
        )
    }
}