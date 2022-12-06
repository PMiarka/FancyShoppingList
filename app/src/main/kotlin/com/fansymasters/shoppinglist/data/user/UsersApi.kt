package com.fansymasters.shoppinglist.data.user

import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {

    @GET("/api/User/getUsers")
    suspend fun searchPhrase(@Query("searchPhrase") searchPhrase: String): List<String>

}
