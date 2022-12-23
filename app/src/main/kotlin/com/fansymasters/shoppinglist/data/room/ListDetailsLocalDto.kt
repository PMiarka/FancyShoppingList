package com.fansymasters.shoppinglist.data.room

data class ListDetailsLocalDto(
    val description: String,
    val id: Int,
    val name: String,
    val shopListItems: List<ListItemLocalDto>,
    val shopListUsers: List<ShopUsersLocalDto>
)