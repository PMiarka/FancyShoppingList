package com.fansymasters.shoppinglist.data.lists

import com.google.gson.annotations.SerializedName

data class ListItemDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("qty") val qty: Int,
    @SerializedName("sortNo") val sortNo: Int,
    @SerializedName("unit") val unit: String,
    @SerializedName("category") val category: String,
    @SerializedName("finished") val finished: Boolean,
)