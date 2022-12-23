package com.fansymasters.shoppinglist.list.details.usecase

import com.fansymasters.shoppinglist.common.commonprocessingstate.CommonProcessingState
import com.fansymasters.shoppinglist.data.room.ListDetailsLocalDto

data class FetchListDetailsState(
    val apiState: CommonProcessingState,
    val details: ListDetailsLocalDto
)