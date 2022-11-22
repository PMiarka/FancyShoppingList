package com.fansymasters.shoppinglist.list.createitem.usecase

import com.fansymasters.shoppinglist.data.apiCall
import com.fansymasters.shoppinglist.data.lists.di.Category
import com.fansymasters.shoppinglist.data.onError
import com.fansymasters.shoppinglist.data.onSuccess
import com.fansymasters.shoppinglist.domain.ProcessingState
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import com.fansymasters.shoppinglist.list.createitem.domain.CreateListItemRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@ViewModelScoped
internal class CreateItemUseCase @Inject constructor(
    private val repository: CreateListItemRepository
) :
    ProcessingStateReader<Unit>,
    CreateItemActions {
    override val state = MutableStateFlow<ProcessingState<Unit>>(ProcessingState.Idle)

    override suspend fun createItem(
        name: String,
        unit: String,
        quantity: Int,
        category: Category,
        listId: Int
    ) {
        state.value = ProcessingState.Processing
        apiCall { repository.createListItem(listId, name, unit, quantity, category) }
            .onSuccess { state.value = ProcessingState.Success(Unit) }
            .onError { state.value = ProcessingState.Error(it) }
    }
}