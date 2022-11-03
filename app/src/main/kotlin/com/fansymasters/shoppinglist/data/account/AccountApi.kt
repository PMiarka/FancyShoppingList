package com.fansymasters.shoppinglist.data.account

import retrofit2.http.Body
import retrofit2.http.POST

internal interface AccountApi {

    @POST("/api/Account/registerGoogle")
    suspend fun registrationGoogle(@Body googleToken: GoogleToken): LoginResponseDto

    @POST("api/account/register")
    suspend fun registrationNormal(@Body registerUserRequest: RegisterUserRequestDto): LoginResponseDto

    @POST("api/account/login")
    suspend fun loginUserNormal(@Body loginUserNormalRequestDto: LoginUserNormalRequestDto): LoginResponseDto

    @POST("api/account/loginGoogle")
    suspend fun loginUserGoogle(@Body googleToken: GoogleToken): LoginResponseDto
}

typealias GoogleToken = String