package com.fansymasters.shoppinglist.account.login.usecase

import com.fansymasters.shoppinglist.account.domain.LoginRepository
import com.fansymasters.shoppinglist.common.CurrentSessionRepository
import com.fansymasters.shoppinglist.data.account.LoginResponseDto
import com.fansymasters.shoppinglist.data.encryptedStorage.EncryptedStorage
import com.fansymasters.shoppinglist.domain.ProcessingState
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@ViewModelScoped
internal class LoginUserUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val currentSessionRepository: CurrentSessionRepository,
    private val encryptedStorage: EncryptedStorage
) : LoginUserActions, ProcessingStateReader<LoginResponseDto> {

    override val state = MutableStateFlow<ProcessingState<LoginResponseDto>>(ProcessingState.Idle)

    override suspend fun loginUserGoogle(token: String) {
        val loginDto = loginRepository.loginUserGoogle(token)
        currentSessionRepository.saveLoginDetails(loginDto)
    }

    override suspend fun loginUserNormal(
        username: String,
        password: String,
        keepLoggedIn: Boolean
    ) {
        val loginDto = loginRepository.loginUserCredentials(username, password)
        currentSessionRepository.saveLoginDetails(loginDto)
        encryptedStorage.setBoolean(KEEP_LOGGED_IN_KEY, keepLoggedIn)
        if (keepLoggedIn) {
            encryptedStorage.setString(USERNAME_KEY, username)
            encryptedStorage.setString(PASSWORD_KEY, password)
        }
    }

    override suspend fun loginAutomaticallyIfUserSaved() {
        val shouldLogin = encryptedStorage.getBoolean(KEEP_LOGGED_IN_KEY, false)
        if (shouldLogin) {
            val username = encryptedStorage.getString(USERNAME_KEY, "")
            val password = encryptedStorage.getString(PASSWORD_KEY, "")
            loginUserNormal(username, password, true)
        } else {
            throw java.lang.IllegalStateException("Cant login automatically")
        }
    }
}

internal const val KEEP_LOGGED_IN_KEY = "KEEP_LOGGED_IN_KEY"
internal const val USERNAME_KEY = "USERNAME_KEY"
internal const val PASSWORD_KEY = "PASSWORD_KEY"