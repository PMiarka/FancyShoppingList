package com.fansymasters.shoppinglist.account.domain

internal interface LoginRepository {
    suspend fun loginUserGoogle(token: String): LoginDto
    suspend fun loginUserCredentials(username: String, password: String): LoginDto
}