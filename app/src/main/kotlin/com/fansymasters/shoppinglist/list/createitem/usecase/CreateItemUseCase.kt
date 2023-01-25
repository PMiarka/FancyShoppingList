package com.fansymasters.shoppinglist.list.createitem.usecase

import com.fansymasters.shoppinglist.data.lists.Category
import com.fansymasters.shoppinglist.list.createitem.domain.CreateListItemRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class CreateItemUseCase @Inject constructor(
    private val repository: CreateListItemRepository
) : CreateItemActions {
    override suspend fun createItem(
        name: String,
        unit: String,
        quantity: Int,
        category: Category,
        listId: Int
    ) {
        repository.createListItem(listId, name, unit, quantity, category)
    }
}