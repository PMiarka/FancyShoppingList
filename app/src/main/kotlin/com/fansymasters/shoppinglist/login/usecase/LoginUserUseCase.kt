package com.fansymasters.shoppinglist.login.usecase

import com.fansymasters.shoppinglist.data.account.AccountApi
import com.fansymasters.shoppinglist.data.account.LoginResponseDto
import com.fansymasters.shoppinglist.data.account.LoginUserNormalRequestDto
import com.fansymasters.shoppinglist.data.apiCall
import com.fansymasters.shoppinglist.data.di.BearerToken
import com.fansymasters.shoppinglist.data.onError
import com.fansymasters.shoppinglist.data.onSuccess
import com.fansymasters.shoppinglist.domain.ProcessingState
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@ViewModelScoped
internal class LoginUserUseCase @Inject constructor(private val api: AccountApi) :
    LoginUserActions, ProcessingStateReader<LoginResponseDto> {

    override val state = MutableStateFlow<ProcessingState<LoginResponseDto>>(ProcessingState.Idle)

    override suspend fun loginUserGoogle(token: String) {
        state.value = ProcessingState.Processing
        apiCall { api.loginUserGoogle(token) }
            .onSuccess {
                BearerToken.token = it.token
                state.value = ProcessingState.Success(it)
            }
            .onError { state.value = ProcessingState.Error(it) }
    }

    override suspend fun loginUserNormal(username: String, password: String) {
        state.value = ProcessingState.Processing
        apiCall { api.loginUserNormal(LoginUserNormalRequestDto(username, password)) }
            .onSuccess {
                BearerToken.token = it.token
                state.value = ProcessingState.Success(it)
            }
            .onError { state.value = ProcessingState.Error(it) }
    }
}