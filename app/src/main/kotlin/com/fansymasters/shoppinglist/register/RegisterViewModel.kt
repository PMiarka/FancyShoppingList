package com.fansymasters.shoppinglist.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fansymasters.shoppinglist.data.account.LoginResponseDto
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import com.fansymasters.shoppinglist.login.usecase.RegisterUserGoogleActions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class RegisterViewModel @Inject constructor(
    private val registerUserGoogleActions: RegisterUserGoogleActions,
    private val processingState: ProcessingStateReader<LoginResponseDto>
) : ViewModel(), ProcessingStateReader<LoginResponseDto> by processingState {

    fun onRegisterClick(username: String, password: String, name: String, email: String) {
        viewModelScope.launch {
            registerUserGoogleActions.registerUserNormal(username, password, name, email)
        }
    }
}