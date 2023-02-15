package com.fansymasters.shoppinglist.list.createitem.usecase

import com.fansymasters.shoppinglist.data.lists.Category
import com.fansymasters.shoppinglist.data.room.CreateListItemLocalDto
import com.fansymasters.shoppinglist.list.createitem.domain.CreateListItemRepository
import com.fansymasters.shoppinglist.list.domain.listdetails.ListDetailsLocalStorageReader
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class CreateItemUseCase @Inject constructor(
    private val repository: CreateListItemRepository,
    private val localStorageReader: ListDetailsLocalStorageReader
) : CreateItemActions {
    override suspend fun createItem(
        item: CreateListItemLocalDto,
        listId: Int
    ) {
        repository.createListItem(item, listId)
    }

    override suspend fun getInitialItem(itemId: Int): CreateListItemLocalDto {
        val localItem = localStorageReader.getItemById(itemId)
        return CreateListItemLocalDto(
            localItem?.id ?: -1,
            localItem?.name ?: "",
            localItem?.qty ?: 1,
            localItem?.sortNo ?: 0,
            localItem?.unit ?: "",
            Category.valueOf(localItem?.category?.uppercase() ?: Category.OTHER.name),
            localItem?.finished ?: false
        )
    }
}