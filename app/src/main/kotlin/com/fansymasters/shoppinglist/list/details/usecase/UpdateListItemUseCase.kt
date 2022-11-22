package com.fansymasters.shoppinglist.list.details.usecase

import com.fansymasters.shoppinglist.data.apiCall
import com.fansymasters.shoppinglist.data.lists.CreateListItemDto
import com.fansymasters.shoppinglist.data.lists.ListItemDto
import com.fansymasters.shoppinglist.data.lists.ListsApi
import com.fansymasters.shoppinglist.data.onError
import com.fansymasters.shoppinglist.data.onSuccess
import com.fansymasters.shoppinglist.domain.ProcessingState
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@ViewModelScoped
internal class UpdateListItemUseCase @Inject constructor(private val api: ListsApi) :
    ProcessingStateReader<ListItemDto>,
    UpdateListItemActions {
    override val state = MutableStateFlow<ProcessingState<ListItemDto>>(ProcessingState.Idle)

    override suspend fun updateItem(listItem: ListItemDto, finished: Boolean) {
        state.value = ProcessingState.Processing
        apiCall { api.updateItem(listItem.id, listItem.toCreateListItem(finished)) }
            .onSuccess { state.value = ProcessingState.Success(it) }
            .onError { state.value = ProcessingState.Error(it) }
    }
}

private fun ListItemDto.toCreateListItem(finished: Boolean): CreateListItemDto = with(this) {
    CreateListItemDto(name, qty, sortNo, unit, category, finished)
}
