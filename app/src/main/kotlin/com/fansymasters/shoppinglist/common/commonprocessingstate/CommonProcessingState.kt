package com.fansymasters.shoppinglist.common.commonprocessingstate

sealed interface CommonProcessingState {
    object Idle : CommonProcessingState
    object Processing : CommonProcessingState
    data class Error(val e: Throwable) : CommonProcessingState
}
