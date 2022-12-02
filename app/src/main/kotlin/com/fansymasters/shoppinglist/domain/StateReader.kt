package com.fansymasters.shoppinglist.domain

import kotlinx.coroutines.flow.Flow

internal interface StateReader<T> {
    val state: Flow<T>
}