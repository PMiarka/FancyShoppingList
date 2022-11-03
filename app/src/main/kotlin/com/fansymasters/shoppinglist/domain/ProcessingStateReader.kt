package com.fansymasters.shoppinglist.domain

import kotlinx.coroutines.flow.StateFlow

internal interface ProcessingStateReader<T> {
    val state: StateFlow<ProcessingState<T>>
}