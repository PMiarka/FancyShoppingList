package com.fansymasters.shoppinglist.common.commonprocessingstate

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@ViewModelScoped
internal class CommonProcessingHandler @Inject constructor() :
    CommonProcessingStateReader,
    CommonProcessingStateWriter {

    override val state = MutableStateFlow<CommonProcessingState>(CommonProcessingState.Idle)

    override fun onLoadingStarted() {
        state.value = CommonProcessingState.Processing
    }

    override fun onError(e: Throwable) {
        state.value = CommonProcessingState.Error(e)
    }

    override fun onSuccess() {
        state.value = CommonProcessingState.Idle
    }
}