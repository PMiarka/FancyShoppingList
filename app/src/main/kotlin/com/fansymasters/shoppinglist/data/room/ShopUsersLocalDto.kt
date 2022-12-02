package com.fansymasters.shoppinglist.data.room

import com.google.gson.annotations.SerializedName

data class ShopUsersLocalDto(
    @SerializedName(value = "permissionType") val permissionType: String,
    @SerializedName(value = "username") val username: String
)