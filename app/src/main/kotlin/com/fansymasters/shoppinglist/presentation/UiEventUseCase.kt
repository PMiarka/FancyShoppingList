package com.fansymasters.shoppinglist.presentation

import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class UiEventUseCase @Inject constructor() : UiEventStateReader, UiEventStateWriter {
    override val uiEventState = MutableSharedFlow<UiEvent>(extraBufferCapacity = 1)

    override fun sendEvent(event: UiEvent) {
        uiEventState.tryEmit(event)
    }
}