package com.fansymasters.shoppinglist.common

import kotlinx.coroutines.flow.SharedFlow

internal interface GeneralErrorHandler {
    val generaleErrorState: SharedFlow<GeneralError>
    fun mapError(e: Throwable)
}

