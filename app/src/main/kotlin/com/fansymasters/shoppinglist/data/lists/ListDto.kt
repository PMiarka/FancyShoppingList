package com.fansymasters.shoppinglist.data.lists

import com.google.gson.annotations.SerializedName


data class ListDto(
    @SerializedName("description") val description: String,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)
