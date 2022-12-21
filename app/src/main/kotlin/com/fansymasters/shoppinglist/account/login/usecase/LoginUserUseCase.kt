package com.fansymasters.shoppinglist.account.login.usecase

import com.fansymasters.shoppinglist.account.domain.LoginRepository
import com.fansymasters.shoppinglist.common.CurrentSessionRepository
import com.fansymasters.shoppinglist.data.account.LoginResponseDto
import com.fansymasters.shoppinglist.domain.ProcessingState
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@ViewModelScoped
internal class LoginUserUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val currentSessionRepository: CurrentSessionRepository
) :
    LoginUserActions, ProcessingStateReader<LoginResponseDto> {

    override val state = MutableStateFlow<ProcessingState<LoginResponseDto>>(ProcessingState.Idle)

    override suspend fun loginUserGoogle(token: String) {
        val loginDto = loginRepository.loginUserGoogle(token)
        currentSessionRepository.saveLoginDetails(loginDto)
    }

    override suspend fun loginUserNormal(username: String, password: String) {
        val loginDto = loginRepository.loginUserCredentials(username, password)
        currentSessionRepository.saveLoginDetails(loginDto)
    }
}