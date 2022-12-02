package com.fansymasters.shoppinglist.presentation

import kotlinx.coroutines.flow.SharedFlow

interface UiEventStateReader {
    val uiEventState: SharedFlow<UiEvent>
}