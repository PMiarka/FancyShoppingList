package com.fansymasters.shoppinglist.data.lists

import com.google.gson.annotations.SerializedName

data class ListItemDto(
    @SerializedName("category") val category: String,
    @SerializedName("finished") val finished: Boolean,
    @SerializedName("name") val name: String,
    @SerializedName("qty") val qty: Int,
    @SerializedName("sortNo") val sortNo: Int,
    @SerializedName("unit") val unit: String
)