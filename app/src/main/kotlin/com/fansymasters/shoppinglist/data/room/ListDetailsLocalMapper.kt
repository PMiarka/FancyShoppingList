package com.fansymasters.shoppinglist.data.room

import com.fansymasters.shoppinglist.common.Mapper
import com.fansymasters.shoppinglist.data.lists.ListDetailsDto
import com.fansymasters.shoppinglist.data.lists.ListItemDto
import com.fansymasters.shoppinglist.data.lists.ShopUsersDto
import javax.inject.Inject

internal class ListDetailsLocalMapper @Inject constructor(
    private val listItemMapper: Mapper<ListItemDto, ListItemLocalDto>,
    private val shopUserMapper: Mapper<ShopUsersDto, ShopUsersLocalDto>
) : Mapper<ListDetailsDto, ListDetailsLocalDto> {
    override fun map(from: ListDetailsDto): ListDetailsLocalDto = with(from) {
        ListDetailsLocalDto(
            id = id,
            name = name,
            description = description,
            shopListItems = shopListItems.map(listItemMapper::map),
            shopListUsers = shopListUsers.map(shopUserMapper::map)
        )
    }
}
