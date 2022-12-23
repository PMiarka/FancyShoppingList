package com.fansymasters.shoppinglist.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal inline fun ViewModel.handleProcessing(
    progressHandler: ProgressHandler,
    crossinline onError: (Throwable) -> Unit = {},
    crossinline onSuccess: () -> Unit = {},
    crossinline action: suspend () -> Unit = {}
): Job =
    viewModelScope.launch(Dispatchers.IO) {
        progressHandler.showProgress()
        kotlin.runCatching { action() }
            .onFailure { onError(it) }
            .onSuccess { onSuccess() }
        progressHandler.hideProgress()
    }

internal inline fun ViewModel.handleProcessing(
    crossinline onError: (Throwable) -> Unit = {},
    crossinline onSuccess: () -> Unit = {},
    crossinline action: suspend () -> Unit = {}
): Job =
    viewModelScope.launch(Dispatchers.IO) {
        kotlin.runCatching { action() }
            .onFailure { onError(it) }
            .onSuccess { onSuccess() }
    }
