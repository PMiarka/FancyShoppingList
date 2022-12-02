package com.fansymasters.shoppinglist.data.room

import com.google.gson.annotations.SerializedName

data class ListDetailsLocalDto(
    @SerializedName(value = "description") val description: String,
    @SerializedName(value = "id") val id: Int,
    @SerializedName(value = "name") val name: String,
    @SerializedName(value = "shopListItems") val shopListItems: List<ListItemLocalDto>,
    @SerializedName(value = "shopListUsers") val shopListUsers: List<ShopUsersLocalDto>
)