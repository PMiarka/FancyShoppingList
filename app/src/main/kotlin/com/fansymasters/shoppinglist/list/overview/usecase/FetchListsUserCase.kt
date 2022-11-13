package com.fansymasters.shoppinglist.list.overview.usecase

import com.fansymasters.shoppinglist.data.apiCall
import com.fansymasters.shoppinglist.data.lists.ListDto
import com.fansymasters.shoppinglist.data.lists.ListsApi
import com.fansymasters.shoppinglist.data.onError
import com.fansymasters.shoppinglist.data.onSuccess
import com.fansymasters.shoppinglist.domain.ProcessingState
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@ViewModelScoped
internal class FetchListsUserCase @Inject constructor(private val api: ListsApi) :
    ProcessingStateReader<List<ListDto>>,
    FetchListsActions {
    override val state = MutableStateFlow<ProcessingState<List<ListDto>>>(ProcessingState.Idle)

    override suspend fun fetchLists() {
        state.value = ProcessingState.Processing
        apiCall { api.fetchUserLists() }
            .onSuccess { state.value = ProcessingState.Success(it) }
            .onError { state.value = ProcessingState.Error(it) }
    }
}