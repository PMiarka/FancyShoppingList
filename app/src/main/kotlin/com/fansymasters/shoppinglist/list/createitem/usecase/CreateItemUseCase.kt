package com.fansymasters.shoppinglist.list.createitem.usecase

import com.fansymasters.shoppinglist.data.room.CreateListItemLocalDto
import com.fansymasters.shoppinglist.list.createitem.domain.CreateListItemRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class CreateItemUseCase @Inject constructor(
    private val repository: CreateListItemRepository
) : CreateItemActions {
    override suspend fun createItem(
        item: CreateListItemLocalDto,
        listId: Int
    ) {
        repository.createListItem(item, listId)
    }
}