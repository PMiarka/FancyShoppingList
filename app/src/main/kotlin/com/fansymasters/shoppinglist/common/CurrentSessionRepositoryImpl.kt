package com.fansymasters.shoppinglist.common

import android.util.Log
import com.fansymasters.shoppinglist.account.domain.LoginDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class CurrentSessionRepositoryImpl @Inject constructor() : CurrentSessionRepository {
    override var token: String = ""
        private set
    override var typeWithToken: String = ""
        get() = "$TOKEN_TYPE $token"
        private set
    override var username: String = ""
        private set

    override fun saveLoginDetails(loginDto: LoginDto) {
        Log.e("Piotrek", "saveLoginDetails: $loginDto")
        token = loginDto.token
        username = loginDto.username
    }
}

internal const val TOKEN_KEY = "Authorization"
private const val TOKEN_TYPE = "Bearer"