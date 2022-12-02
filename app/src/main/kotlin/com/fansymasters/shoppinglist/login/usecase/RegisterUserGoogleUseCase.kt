package com.fansymasters.shoppinglist.login.usecase

import android.util.Log
import com.fansymasters.shoppinglist.common.noMapper
import com.fansymasters.shoppinglist.data.account.AccountApi
import com.fansymasters.shoppinglist.data.account.RegisterUserRequestDto
import com.fansymasters.shoppinglist.data.apiCall
import com.fansymasters.shoppinglist.data.onError
import com.fansymasters.shoppinglist.data.onSuccess
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class RegisterUserGoogleUseCase @Inject constructor(private val api: AccountApi) :
    RegisterUserGoogleActions {

    override suspend fun registerUserGoogle(token: String) {
        apiCall(noMapper()) { api.registrationGoogle(token) }
            .onSuccess { Log.e("-->", "$it") }
            .onError { Log.e("-->", "error: $it") }
    }

    override suspend fun registerUserNormal(
        username: String,
        password: String,
        name: String,
        email: String
    ) {
        apiCall(noMapper()) {
            api.registrationNormal(
                RegisterUserRequestDto(
                    userName = username,
                    password = password,
                    name = name,
                    email = email
                )
            )
        }
            .onSuccess { Log.e("-->", "$it") }
            .onError { Log.e("-->", "error: $it") }
    }
}