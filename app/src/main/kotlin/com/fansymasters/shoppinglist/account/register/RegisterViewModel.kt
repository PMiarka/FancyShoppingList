package com.fansymasters.shoppinglist.account.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fansymasters.shoppinglist.account.login.usecase.RegisterUserGoogleActions
import com.fansymasters.shoppinglist.common.ProgressHandler
import com.fansymasters.shoppinglist.common.handleProcessing
import com.fansymasters.shoppinglist.data.account.LoginResponseDto
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import com.fansymasters.shoppinglist.list.navigation.ListsNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class RegisterViewModel @Inject constructor(
    private val registerUserGoogleActions: RegisterUserGoogleActions,
    private val progressHandler: ProgressHandler,
    private val listsNavigation: ListsNavigation,
    ) : ViewModel() {

    fun onRegisterClick(username: String, password: String, name: String, email: String) {
        handleProcessing(
            progressHandler = progressHandler,
            onSuccess = listsNavigation::openListsOverview
        ) {
            registerUserGoogleActions.registerUserNormal(username, password, name, email)
        }
    }
}