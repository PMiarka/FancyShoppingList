package com.fansymasters.shoppinglist.data.room

import com.fansymasters.shoppinglist.common.Mapper
import com.fansymasters.shoppinglist.data.lists.ListItemDto
import javax.inject.Inject

internal class ListItemMapper @Inject constructor() : Mapper<ListItemDto, ListItemLocalDto> {
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
