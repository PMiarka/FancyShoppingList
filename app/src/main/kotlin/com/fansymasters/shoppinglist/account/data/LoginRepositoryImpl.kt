package com.fansymasters.shoppinglist.account.data

import com.fansymasters.shoppinglist.account.domain.LoginDto
import com.fansymasters.shoppinglist.account.domain.LoginRepository
import com.fansymasters.shoppinglist.common.Mapper
import com.fansymasters.shoppinglist.data.account.AccountApi
import com.fansymasters.shoppinglist.data.account.LoginResponseDto
import com.fansymasters.shoppinglist.data.account.LoginUserNormalRequestDto
import com.fansymasters.shoppinglist.data.apiCall
import javax.inject.Inject

internal class LoginRepositoryImpl @Inject constructor(
    private val api: AccountApi, private val loginResponseMapper: Mapper<LoginResponseDto, LoginDto>
) : LoginRepository {
    override suspend fun loginUserGoogle(token: String): LoginDto =
        apiCall(loginResponseMapper::map) { api.loginUserGoogle(token) }

    override suspend fun loginUserCredentials(
        username: String, password: String
    ): LoginDto = apiCall(loginResponseMapper::map) {
        api.loginUserNormal(LoginUserNormalRequestDto(userName = username, password = password))
    }
}
