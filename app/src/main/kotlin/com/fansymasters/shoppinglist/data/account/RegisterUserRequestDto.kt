package com.fansymasters.shoppinglist.data.account

data class RegisterUserRequestDto(
    val userName: String,
    val password: String,
    val name: String,
    val email: String,
)
