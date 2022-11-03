package com.fansymasters.shoppinglist.domain

sealed interface ProcessingState<out T> {
    object Idle : ProcessingState<Nothing>
    object Processing : ProcessingState<Nothing>
    data class Success<T>(val data: T) : ProcessingState<T>
    data class Error(val error: FancyError) : ProcessingState<Nothing>
}