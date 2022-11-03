package com.fansymasters.shoppinglist.data.lists

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

internal interface ListsApi {

    @GET("/api/List")
    suspend fun fetchUserLists(): List<ListDto>

    @POST("/api/List")
    suspend fun createList(@Body listToCreate: CreateListDto): ListDto
}
