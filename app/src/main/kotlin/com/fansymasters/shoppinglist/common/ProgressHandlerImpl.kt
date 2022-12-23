package com.fansymasters.shoppinglist.common

import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ProgressHandlerImpl @Inject constructor() : ProgressHandler {
    override val progressState = MutableSharedFlow<Boolean>()

    override suspend fun showProgress() {
        progressState.emit(true)
    }

    override suspend fun hideProgress() {
        progressState.emit(false)
    }
}