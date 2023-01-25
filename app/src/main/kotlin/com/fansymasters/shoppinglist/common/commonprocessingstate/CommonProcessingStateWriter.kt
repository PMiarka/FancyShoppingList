package com.fansymasters.shoppinglist.common.commonprocessingstate

interface CommonProcessingStateWriter {
    suspend fun onLoadingStarted()
    suspend fun onError(e: Throwable)
    suspend fun onSuccess()
}