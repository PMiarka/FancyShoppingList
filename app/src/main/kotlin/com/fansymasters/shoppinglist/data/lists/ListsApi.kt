package com.fansymasters.shoppinglist.data.lists

import retrofit2.http.*

internal interface ListsApi {

    @GET("/api/List")
    suspend fun fetchUserLists(): List<ListDto>

    @POST("/api/List")
    suspend fun createList(@Body listToCreate: CreateListDto): ListDto

    @GET("/api/List/{listId}")
    suspend fun fetchListDetails(@Path("listId") listId: Int): ListDetailsDto

    @POST("/api/ListItem/{listId}")
    suspend fun createItem(
        @Path("listId") listId: Int,
        @Body itemToCreate: CreateListItemDto
    ): ListItemDto

    @PUT("/api/ListItem/{itemId}")
    suspend fun updateItem(
        @Path("itemId") listId: Int,
        @Body itemToCreate: CreateListItemDto
    ): ListItemDto

    @DELETE("/api/ListItem/{itemId}")
    suspend fun deleteItem(@Path("itemId") itemId: Int)
}
