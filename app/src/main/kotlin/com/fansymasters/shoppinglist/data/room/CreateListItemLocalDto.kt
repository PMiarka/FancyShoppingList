package com.fansymasters.shoppinglist.data.room

import com.fansymasters.shoppinglist.data.lists.Category

data class CreateListItemLocalDto(
    val name: String,
    val qty: Int,
    val sortNo: Int,
    val unit: String,
    val category: Category,
    val finished: Boolean,
)
