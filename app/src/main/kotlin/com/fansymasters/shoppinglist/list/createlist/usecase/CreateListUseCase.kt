package com.fansymasters.shoppinglist.list.createlist.usecase

import com.fansymasters.shoppinglist.data.apiCall
import com.fansymasters.shoppinglist.data.lists.CreateListDto
import com.fansymasters.shoppinglist.data.lists.ListDto
import com.fansymasters.shoppinglist.data.lists.ListsApi
import com.fansymasters.shoppinglist.data.onError
import com.fansymasters.shoppinglist.data.onSuccess
import com.fansymasters.shoppinglist.domain.ProcessingState
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import com.fansymasters.shoppinglist.list.domain.ListDetailsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@ViewModelScoped
internal class CreateListUseCase @Inject constructor(
    private val api: ListsApi,
    private val repository: ListDetailsRepository
) :
    ProcessingStateReader<ListDto>,
    CreateListActions {
    override val state = MutableStateFlow<ProcessingState<ListDto>>(ProcessingState.Idle)

    override suspend fun createList(name: String, description: String) {
        state.value = ProcessingState.Processing
        apiCall { api.createList(CreateListDto(name = name, description = description)) }
            .onSuccess { state.value = ProcessingState.Success(it) }
            .onError { state.value = ProcessingState.Error(it) }
    }
}