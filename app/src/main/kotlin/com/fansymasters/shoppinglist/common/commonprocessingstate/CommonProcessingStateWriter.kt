package com.fansymasters.shoppinglist.common.commonprocessingstate

interface CommonProcessingStateWriter {
    fun onLoadingStarted()
    fun onError(e: Throwable)
    fun onSuccess()
}