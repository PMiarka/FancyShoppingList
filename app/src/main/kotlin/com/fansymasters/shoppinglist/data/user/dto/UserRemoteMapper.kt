package com.fansymasters.shoppinglist.data.user.dto

import com.fansymasters.shoppinglist.common.Mapper
import com.fansymasters.shoppinglist.searchuser.domain.UserDomainDto
import javax.inject.Inject

internal class UserRemoteMapper @Inject constructor() : Mapper<String, UserDomainDto> {
    override fun map(from: String): UserDomainDto = UserDomainDto(from)
}
