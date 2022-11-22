package com.fansymasters.shoppinglist.list.details.usecase

import com.fansymasters.shoppinglist.data.room.ListItemLocalDto
import com.fansymasters.shoppinglist.domain.ProcessingState

data class FetchListDetailsState(
    val apiState: ProcessingState<Unit>,
    val items: List<ListItemLocalDto>
)