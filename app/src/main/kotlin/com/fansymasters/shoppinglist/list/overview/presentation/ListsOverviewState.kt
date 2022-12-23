package com.fansymasters.shoppinglist.list.overview.presentation

import com.fansymasters.shoppinglist.list.domain.lists.ListLocalDto

sealed interface ListsOverviewState {
    val lists: List<ListLocalDto>

    data class Idle(override val lists: List<ListLocalDto>) : ListsOverviewState
    data class Loading(override val lists: List<ListLocalDto>) : ListsOverviewState
    data class Error(override val lists: List<ListLocalDto>, val error: Throwable) :
        ListsOverviewState
}
