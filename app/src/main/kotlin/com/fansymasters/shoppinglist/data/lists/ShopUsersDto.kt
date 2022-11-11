package com.fansymasters.shoppinglist.data.lists

import com.google.gson.annotations.SerializedName

data class ShopUsersDto(
    @SerializedName(value = "permissionType") val permissionType: String,
    @SerializedName(value = "username") val username: String
)