package com.fansymasters.shoppinglist.presentation

interface UiEventStateWriter {
    fun sendEvent(event: UiEvent)
}