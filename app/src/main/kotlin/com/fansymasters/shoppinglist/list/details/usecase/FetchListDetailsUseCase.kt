package com.fansymasters.shoppinglist.list.details.usecase

import com.fansymasters.shoppinglist.common.noMapper
import com.fansymasters.shoppinglist.data.apiCall
import com.fansymasters.shoppinglist.data.onError
import com.fansymasters.shoppinglist.data.onSuccess
import com.fansymasters.shoppinglist.domain.ProcessingState
import com.fansymasters.shoppinglist.domain.StateReader
import com.fansymasters.shoppinglist.list.domain.ListDetailsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@ViewModelScoped
internal class FetchListDetailsUseCase @Inject constructor(
    private val repository: ListDetailsRepository
) : StateReader<FetchListDetailsState>,
    FetchListDetailsActions {
    private val apiState = MutableStateFlow<ProcessingState<Unit>>(ProcessingState.Idle)

    override val state = repository.localState.combine(apiState) { localState, apiState ->
        FetchListDetailsState(apiState, localState)
    }

    override suspend fun fetchListDetails(listId: Int) {
        apiState.value = ProcessingState.Processing
        apiCall(noMapper()) { repository.fetchListItems(listId) }
            .onSuccess { apiState.value = ProcessingState.Success(Unit) }
            .onError { apiState.value = ProcessingState.Error(it) }
    }
}