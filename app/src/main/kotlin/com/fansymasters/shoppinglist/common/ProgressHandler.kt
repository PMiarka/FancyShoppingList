package com.fansymasters.shoppinglist.common

import kotlinx.coroutines.flow.SharedFlow

internal interface ProgressHandler {
    val progressState: SharedFlow<Boolean>
    suspend fun showProgress()
    suspend fun hideProgress()
}