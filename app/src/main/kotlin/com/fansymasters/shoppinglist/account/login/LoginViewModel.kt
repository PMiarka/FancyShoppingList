package com.fansymasters.shoppinglist.account.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fansymasters.shoppinglist.NavigationWriter
import com.fansymasters.shoppinglist.account.login.usecase.LoginUserActions
import com.fansymasters.shoppinglist.account.login.usecase.RegisterUserGoogleActions
import com.fansymasters.shoppinglist.common.GeneralErrorHandler
import com.fansymasters.shoppinglist.common.ProgressHandler
import com.fansymasters.shoppinglist.common.handleProcessing
import com.fansymasters.shoppinglist.list.navigation.ListsNavigation
import com.fansymasters.shoppinglist.presentation.UiEventStateWriter
import com.fansymasters.shoppinglist.ui.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val registerUserGoogleActions: RegisterUserGoogleActions,
    private val loginUserActions: LoginUserActions,
    private val navigationWriter: NavigationWriter,
    private val listsNavigation: ListsNavigation,
    progressHandler: ProgressHandler,
    private val generalErrorHandler: GeneralErrorHandler,
    private val uiEventStateWriter: UiEventStateWriter
) : ViewModel(),
    ProgressHandler by progressHandler {

    init {
        onLogInClick("pimi2", "1234")
    }

    fun onLogInClick(username: String, password: String) {
        handleProcessing(
            progressHandler = this,
            onError = generalErrorHandler::mapError,
            onSuccess = listsNavigation::openListsOverview
        ) {
            loginUserActions.loginUserNormal(username, password)
        }
    }

    fun onRegisterUsingGoogleAccountClick(token: String) {
        viewModelScope.launch {
            registerUserGoogleActions.registerUserGoogle(token)
        }
    }

    fun onSignInClick() {
        navigationWriter.navigate(NavigationRoutes.Authentication.Register)
    }
}

