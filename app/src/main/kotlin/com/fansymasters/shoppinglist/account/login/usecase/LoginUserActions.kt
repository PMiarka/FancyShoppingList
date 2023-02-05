package com.fansymasters.shoppinglist.account.login.usecase

internal interface LoginUserActions {
    suspend fun loginAutomaticallyIfUserSaved()
    suspend fun loginUserGoogle(token: String)
    suspend fun loginUserNormal(username: String, password: String, keepLoggedIn: Boolean)
}
