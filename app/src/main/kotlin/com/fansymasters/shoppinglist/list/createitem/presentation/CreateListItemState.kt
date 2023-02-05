package com.fansymasters.shoppinglist.list.createitem.presentation

import com.fansymasters.shoppinglist.data.room.CreateListItemLocalDto

sealed interface CreateListItemState {
    val item: CreateListItemLocalDto

    data class Idle(override val item: CreateListItemLocalDto) : CreateListItemState
    data class Progress(override val item: CreateListItemLocalDto) : CreateListItemState
    data class Error(
        override val item: CreateListItemLocalDto,
        val error: Throwable
    ) : CreateListItemState
}
