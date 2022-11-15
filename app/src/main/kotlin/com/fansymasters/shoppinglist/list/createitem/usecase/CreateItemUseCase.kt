package com.fansymasters.shoppinglist.list.createitem.usecase

import com.fansymasters.shoppinglist.data.apiCall
import com.fansymasters.shoppinglist.data.lists.CreateListItemDto
import com.fansymasters.shoppinglist.data.lists.ListsApi
import com.fansymasters.shoppinglist.data.lists.di.Category
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

    override suspend fun createItem(
        name: String,
        unit: String,
        quantity: Int,
        category: Category,
        listId: Int
    ) {
        state.value = ProcessingState.Processing
        apiCall { api.createItem(listId, createItem(name, unit, quantity, category)) }
            .onSuccess { state.value = ProcessingState.Success(Unit) }
            .onError { state.value = ProcessingState.Error(it) }
    }

    fun createItem(name: String, unit: String, quantity: Int, category: Category) =
        CreateListItemDto(
            name = name,
            qty = quantity,
            sortNo = 200,
            unit = unit,
            category = category.apiKey,
            finished = false
        )
}