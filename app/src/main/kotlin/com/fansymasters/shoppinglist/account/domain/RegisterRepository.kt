package com.fansymasters.shoppinglist.account.domain

internal interface RegisterRepository {
    suspend fun registerUserGoogle(token: String): LoginDto
    suspend fun registerUserCredentials(
        username: String,
        password: String,
        name: String,
        email: String
    ): LoginDto
}