package com.fansymasters.shoppinglist.data.lists

import com.google.gson.annotations.SerializedName


data class CreateListDto(
    @SerializedName("description") val description: String,
    @SerializedName("name") val name: String
)
