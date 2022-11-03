package com.fansymasters.shoppinglist.login.usecase

internal interface RegisterUserGoogleActions {
    suspend fun registerUserGoogle(token: String)
    suspend fun registerUserNormal(username: String, password: String, name: String, email: String)
}
