package com.fansymasters.shoppinglist.account.login.usecase

internal interface RegisterUserGoogleActions {
    suspend fun registerUserGoogle(token: String)
    suspend fun registerUserNormal(username: String, password: String, name: String, email: String)
}
