package com.fansymasters.shoppinglist.data.account

import com.google.gson.annotations.SerializedName


data class LoginResponseDto(
    @SerializedName("username") val username: String,
    @SerializedName("token") val token: String
)
