package com.fansymasters.shoppinglist.list.details.usecase

import com.fansymasters.shoppinglist.data.room.ListItemLocalDto
import com.fansymasters.shoppinglist.domain.ProcessingState
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import com.fansymasters.shoppinglist.list.domain.ListDetailsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@ViewModelScoped
internal class UpdateListItemUseCase @Inject constructor(private val repository: ListDetailsRepository) :
    ProcessingStateReader<ListItemLocalDto>,
    UpdateListItemActions {
    override val state = MutableStateFlow<ProcessingState<ListItemLocalDto>>(ProcessingState.Idle)

    override suspend fun updateItem(listItem: ListItemLocalDto) {
        state.value = ProcessingState.Processing
        repository.updateItem(listItem)
    }

    override suspend fun setItemFinished(listItem: ListItemLocalDto) {
        state.value = ProcessingState.Processing
        repository.setItemFinished(listItem.id, !listItem.finished)
    }
}

