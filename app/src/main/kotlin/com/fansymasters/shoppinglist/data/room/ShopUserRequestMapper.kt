package com.fansymasters.shoppinglist.data.room

import com.fansymasters.shoppinglist.common.Mapper
import com.fansymasters.shoppinglist.data.lists.ShopUsersDto
import javax.inject.Inject

internal class ShopUserRequestMapper @Inject constructor() :
    Mapper<ShopUsersLocalDto, ShopUsersDto> {
    override fun map(from: ShopUsersLocalDto): ShopUsersDto = with(from) {
        ShopUsersDto(
            permissionType = permissionType,
            username = username
        )
    }
}
