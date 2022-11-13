package com.fansymasters.shoppinglist.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fansymasters.shoppinglist.NavigationWriter
import com.fansymasters.shoppinglist.data.account.LoginResponseDto
import com.fansymasters.shoppinglist.domain.ProcessingState
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import com.fansymasters.shoppinglist.list.navigation.ListsNavigation
import com.fansymasters.shoppinglist.login.usecase.LoginUserActions
import com.fansymasters.shoppinglist.login.usecase.RegisterUserGoogleActions
import com.fansymasters.shoppinglist.ui.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val registerUserGoogleActions: RegisterUserGoogleActions,
    private val loginUserActions: LoginUserActions,
    private val navigationWriter: NavigationWriter,
    private val listsNavigation: ListsNavigation,
    private val processingState: ProcessingStateReader<LoginResponseDto>
) : ViewModel(), ProcessingStateReader<LoginResponseDto> by processingState {

    init {
        state.filterIsInstance<ProcessingState.Success<LoginResponseDto>>()
            .onEach {
                listsNavigation.openListsOverview()
            }
            .launchIn(viewModelScope)
        onLogInClick("pimi2", "1234")
    }

    fun onLogInClick(username: String, password: String) {
        viewModelScope.launch {
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