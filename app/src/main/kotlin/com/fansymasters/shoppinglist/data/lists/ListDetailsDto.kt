package com.fansymasters.shoppinglist.data.lists

import com.google.gson.annotations.SerializedName

data class ListDetailsDto(
    @SerializedName(value = "description") val description: String,
    @SerializedName(value = "id") val id: Int,
    @SerializedName(value = "name") val name: String,
    @SerializedName(value = "shopListItems") val shopListItems: List<ListItemDto>,
    @SerializedName(value = "shopListUsers") val shopListUsers: List<ShopUsersDto>
)