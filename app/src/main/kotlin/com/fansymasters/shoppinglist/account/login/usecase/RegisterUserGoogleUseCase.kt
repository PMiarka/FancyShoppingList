package com.fansymasters.shoppinglist.account.login.usecase

import com.fansymasters.shoppinglist.account.domain.RegisterRepository
import com.fansymasters.shoppinglist.common.CurrentSessionRepository
import com.fansymasters.shoppinglist.common.noMapper
import com.fansymasters.shoppinglist.data.account.AccountApi
import com.fansymasters.shoppinglist.data.account.RegisterUserRequestDto
import com.fansymasters.shoppinglist.data.apiCall
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class RegisterUserGoogleUseCase @Inject constructor(
    private val repository: RegisterRepository,
    private val currentSessionRepository: CurrentSessionRepository
) : RegisterUserGoogleActions {

    override suspend fun registerUserGoogle(token: String) {
        repository.registerUserGoogle(token)

    }

    override suspend fun registerUserNormal(
        username: String,
        password: String,
        name: String,
        email: String
    ) {
        val loginDto = repository.registerUserCredentials(
            username = username,
            password = password,
            name = name,
            email = email
        )
        currentSessionRepository.saveLoginDetails(loginDto)
    }
}