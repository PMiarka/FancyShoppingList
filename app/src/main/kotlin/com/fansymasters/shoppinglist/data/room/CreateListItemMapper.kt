package com.fansymasters.shoppinglist.data.room

import com.fansymasters.shoppinglist.common.Mapper
import com.fansymasters.shoppinglist.data.lists.CreateListItemDto
import com.fansymasters.shoppinglist.data.lists.ListItemDto
import javax.inject.Inject

internal class CreateListItemMapper @Inject constructor() :
    Mapper<CreateListItemLocalDto, CreateListItemDto> {

    override fun map(from: CreateListItemLocalDto): CreateListItemDto =
        with(from) {
            CreateListItemDto(
                name = name,
                unit = unit,
                qty = qty,
                sortNo = 200,
                category = category.apiKey,
                finished = false
            )
        }
}
