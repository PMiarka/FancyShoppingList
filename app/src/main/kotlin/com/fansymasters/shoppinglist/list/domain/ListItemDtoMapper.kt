package com.fansymasters.shoppinglist.list.domain

import com.fansymasters.shoppinglist.common.Mapper
import com.fansymasters.shoppinglist.data.lists.ListItemDto
import com.fansymasters.shoppinglist.data.room.ListItemLocalDto
import javax.inject.Inject

internal class ListItemDtoMapper @Inject constructor() : Mapper<ListItemDto, ListItemLocalDto> {
    override fun map(from: ListItemDto): ListItemLocalDto = with(from) {
        ListItemLocalDto(
            id = id,
            name = name,
            qty = qty,
            sortNo = sortNo,
            unit = unit,
            category = category,
            finished = finished
        )
    }
}
