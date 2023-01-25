package com.fansymasters.shoppinglist.common

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fansymasters.shoppinglist.common.commonprocessingstate.CommonProcessingStateWriter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal inline fun ViewModel.handleProcessing(
    progressHandler: ProgressHandler,
    crossinline onError: (Throwable) -> Unit = {},
    crossinline onSuccess: () -> Unit = {},
    crossinline action: suspend () -> Unit = {}
): Job =
    viewModelScope.launch() {
        progressHandler.showProgress()
        runCatching { action() }
            .onFailure { onError(it) }
            .onSuccess {
                Log.e("Piotrek", "HandleProcessingSuccess")
                onSuccess() }
            .also {
                Log.e("Piotrek", "HandleProcessing hide progress")
                progressHandler.hideProgress() }
    }

internal inline fun ViewModel.handleProcessing(
    crossinline onError: suspend (Throwable) -> Unit = {},
    crossinline onSuccess: suspend () -> Unit = {},
    crossinline action: suspend () -> Unit = {}
): Job =
    viewModelScope.launch(Dispatchers.IO) {
        runCatching { action() }
            .onFailure { onError(it) }
            .onSuccess { onSuccess() }
    }

internal inline fun ViewModel.handleProcessing(
    processingStateWriter: CommonProcessingStateWriter,
    crossinline action: suspend () -> Unit = {}
): Job =
    viewModelScope.launch(Dispatchers.IO) {
        processingStateWriter.onLoadingStarted()
        runCatching { action() }
            .onFailure { processingStateWriter.onError(it) }
            .onSuccess { processingStateWriter.onSuccess() }
    }
