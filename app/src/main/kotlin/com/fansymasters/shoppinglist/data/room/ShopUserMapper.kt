package com.fansymasters.shoppinglist.data.room

import com.fansymasters.shoppinglist.common.Mapper
import com.fansymasters.shoppinglist.data.lists.ShopUsersDto
import javax.inject.Inject

internal class ShopUserMapper @Inject constructor() : Mapper<ShopUsersDto, ShopUsersLocalDto> {
    override fun map(from: ShopUsersDto): ShopUsersLocalDto = with(from) {
        ShopUsersLocalDto(
            permissionType = permissionType,
            username = username
        )
    }
}
