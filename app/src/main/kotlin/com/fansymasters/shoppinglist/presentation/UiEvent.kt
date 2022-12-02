package com.fansymasters.shoppinglist.presentation

sealed interface UiEvent {
    object Idle : UiEvent
    data class ShowToast(val message: String) : UiEvent
}