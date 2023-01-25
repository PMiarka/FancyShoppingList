package com.fansymasters.shoppinglist.common

import android.util.Log
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ProgressHandlerImpl @Inject constructor() : ProgressHandler {
    override val progressState = MutableSharedFlow<Boolean>()

    override suspend fun showProgress() {
        Log.e("Piotrek", "ShowProgress")

        progressState.emit(true)
    }

    override suspend fun hideProgress() {
        Log.e("Piotrek", "HideProgress")
        progressState.emit(false)
    }
}