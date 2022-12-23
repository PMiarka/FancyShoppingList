package com.fansymasters.shoppinglist.common.commonprocessingstate

import kotlinx.coroutines.flow.StateFlow

interface CommonProcessingStateReader {
    val state: StateFlow<CommonProcessingState>
}