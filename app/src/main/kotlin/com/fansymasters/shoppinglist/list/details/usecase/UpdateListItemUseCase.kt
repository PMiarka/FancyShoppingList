package com.fansymasters.shoppinglist.list.details.usecase

import com.fansymasters.shoppinglist.data.room.ListItemLocalDto
import com.fansymasters.shoppinglist.list.domain.listdetails.ListDetailsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class UpdateListItemUseCase @Inject constructor(
    private val repository: ListDetailsRepository
) : UpdateListItemActions {

    override suspend fun updateItem(listItem: ListItemLocalDto) {
        repository.updateItem(listItem)
    }

    override suspend fun setItemFinished(listItem: ListItemLocalDto) {
        repository.setItemFinished(listItem.id, !listItem.finished)
    }
}

