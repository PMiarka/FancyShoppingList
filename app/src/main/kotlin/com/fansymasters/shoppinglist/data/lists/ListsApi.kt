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
        @Path("itemId") itemId: Int,
        @Body itemToCreate: CreateListItemDto
    )

    @POST("/api/ListItem/setPermissions/{listId}")
    suspend fun setUsersPermissions(
        @Path("listId") itemId: Int,
        @Body userPermissions: List<ShopUsersDto>
    )

    @POST("/api/ListItem/setFinished/{itemId}")
    suspend fun setItemFinished(@Path("itemId") itemId: Int, @Body isFinished: Boolean)

    @DELETE("/api/ListItem/{itemId}")
    suspend fun deleteItem(@Path("itemId") itemId: Int)
}
