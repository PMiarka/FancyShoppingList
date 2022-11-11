package com.fansymasters.shoppinglist.list.details.usecase

import com.fansymasters.shoppinglist.data.apiCall
import com.fansymasters.shoppinglist.data.lists.ListDetailsDto
import com.fansymasters.shoppinglist.data.lists.ListsApi
import com.fansymasters.shoppinglist.data.onError
import com.fansymasters.shoppinglist.data.onSuccess
import com.fansymasters.shoppinglist.domain.ProcessingState
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@ViewModelScoped
internal class FetchListDetailsUseCase @Inject constructor(private val api: ListsApi) :
    ProcessingStateReader<ListDetailsDto>,
    FetchListDetailsActions {
    override val state = MutableStateFlow<ProcessingState<ListDetailsDto>>(ProcessingState.Idle)

    override suspend fun fetchListDetails(listId: Int) {
        state.value = ProcessingState.Processing
        apiCall { api.fetchListDetails(listId) }
            .onSuccess { state.value = ProcessingState.Success(it) }
            .onError { state.value = ProcessingState.Error(it) }
    }
}