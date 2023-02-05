package com.fansymasters.shoppinglist.account.data

import com.fansymasters.shoppinglist.account.domain.LoginDto
import com.fansymasters.shoppinglist.account.domain.LoginRepository
import com.fansymasters.shoppinglist.account.domain.RegisterRepository
import com.fansymasters.shoppinglist.common.Mapper
import com.fansymasters.shoppinglist.data.account.AccountApi
import com.fansymasters.shoppinglist.data.account.LoginResponseDto
import com.fansymasters.shoppinglist.data.account.LoginUserNormalRequestDto
import com.fansymasters.shoppinglist.data.account.RegisterUserRequestDto
import com.fansymasters.shoppinglist.data.apiCall
import javax.inject.Inject

internal class RegisterRepositoryImpl @Inject constructor(
    private val api: AccountApi, private val loginResponseMapper: Mapper<LoginResponseDto, LoginDto>
) : RegisterRepository {

     override suspend fun registerUserGoogle(token: String): LoginDto =
        apiCall(loginResponseMapper::map) { api.registrationGoogle(token) }


    override suspend fun registerUserCredentials(
        username: String,
        password: String,
        name: String,
        email: String
    ): LoginDto =
        apiCall(loginResponseMapper::map) {
            api.registrationNormal(
                RegisterUserRequestDto(
                    userName = username,
                    password = password,
                    name = name,
                    email = email
                )
            )
        }
}
