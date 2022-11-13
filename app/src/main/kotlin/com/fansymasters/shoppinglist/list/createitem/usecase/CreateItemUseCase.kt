package com.fansymasters.shoppinglist.list.createitem.usecase

import com.fansymasters.shoppinglist.data.apiCall
import com.fansymasters.shoppinglist.data.lists.CreateListItemDto
import com.fansymasters.shoppinglist.data.lists.ListsApi
import com.fansymasters.shoppinglist.data.onError
import com.fansymasters.shoppinglist.data.onSuccess
import com.fansymasters.shoppinglist.domain.ProcessingState
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@ViewModelScoped
internal class CreateItemUseCase @Inject constructor(private val api: ListsApi) :
    ProcessingStateReader<Unit>,
    CreateItemActions {
    override val state = MutableStateFlow<ProcessingState<Unit>>(ProcessingState.Idle)

    override suspend fun createItem(name: String, listId: Int) {
        state.value = ProcessingState.Processing
        apiCall { api.createItem(listId, createItem(name)) }
            .onSuccess { state.value = ProcessingState.Success(Unit) }
            .onError { state.value = ProcessingState.Error(it) }
    }

    fun createItem(name: String) = CreateListItemDto(
        name = name,
        qty = 1,
        sortNo = 200,
        unit = "szt",
        category = "VegAndFruits",
        finished = false
    )
}