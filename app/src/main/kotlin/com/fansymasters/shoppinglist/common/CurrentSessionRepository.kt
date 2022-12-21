package com.fansymasters.shoppinglist.common

import com.fansymasters.shoppinglist.account.domain.LoginDto

internal interface CurrentSessionRepository {
    val token: String
    val typeWithToken: String
    val username: String
    fun saveLoginDetails(loginDto: LoginDto)
}