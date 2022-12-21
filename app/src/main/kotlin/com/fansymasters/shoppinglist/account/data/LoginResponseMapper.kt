package com.fansymasters.shoppinglist.account.data

import com.fansymasters.shoppinglist.account.domain.LoginDto
import com.fansymasters.shoppinglist.common.Mapper
import com.fansymasters.shoppinglist.data.account.LoginResponseDto
import javax.inject.Inject

internal class LoginResponseMapper @Inject constructor() : Mapper<LoginResponseDto, LoginDto> {
    override fun map(from: LoginResponseDto): LoginDto = with(from) {
        LoginDto(username = username, token = token)
    }
}