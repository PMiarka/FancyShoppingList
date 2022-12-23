package com.fansymasters.shoppinglist.list.data.lists

import com.fansymasters.shoppinglist.common.Mapper
import com.fansymasters.shoppinglist.data.lists.ListDto
import com.fansymasters.shoppinglist.list.domain.lists.ListLocalDto
import javax.inject.Inject

internal class ListDtoMapper @Inject constructor() : Mapper<ListDto, ListLocalDto> {
    override fun map(from: ListDto): ListLocalDto = with(from) {
        ListLocalDto(
            id = from.id,
            name = from.name,
            description = from.description
        )
    }
}