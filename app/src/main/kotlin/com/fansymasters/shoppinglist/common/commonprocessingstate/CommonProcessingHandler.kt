package com.fansymasters.shoppinglist.common.commonprocessingstate

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@ViewModelScoped
internal class CommonProcessingHandler @Inject constructor() :
    CommonProcessingStateReader,
    CommonProcessingStateWriter {

    override val state = MutableStateFlow<CommonProcessingState>(CommonProcessingState.Idle)

    override suspend fun onLoadingStarted() {
        state.emit(CommonProcessingState.Processing)
    }

    override suspend fun onError(e: Throwable) {
        state.emit(CommonProcessingState.Error(e))
    }

    override suspend fun onSuccess() {
        state.emit(CommonProcessingState.Idle)
    }
}