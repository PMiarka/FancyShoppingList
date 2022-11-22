package com.fansymasters.shoppinglist.list.details.usecase

import com.fansymasters.shoppinglist.data.apiCall
import com.fansymasters.shoppinglist.data.lists.ListItemDto
import com.fansymasters.shoppinglist.data.onError
import com.fansymasters.shoppinglist.data.onSuccess
import com.fansymasters.shoppinglist.domain.ProcessingState
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import com.fansymasters.shoppinglist.list.domain.ListDetailsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@ViewModelScoped
internal class DeleteListItemUseCase @Inject constructor(private val repository: ListDetailsRepository) :
    ProcessingStateReader<ListItemDto>,
    DeleteListItemActions {
    override val state = MutableStateFlow<ProcessingState<ListItemDto>>(ProcessingState.Idle)


    override suspend fun deleteItem(itemId: Int) {
        state.value = ProcessingState.Processing
        apiCall { repository.deleteItem(itemId) }
            .onSuccess { state.value = ProcessingState.Idle }
            .onError { state.value = ProcessingState.Error(it) }
    }
}