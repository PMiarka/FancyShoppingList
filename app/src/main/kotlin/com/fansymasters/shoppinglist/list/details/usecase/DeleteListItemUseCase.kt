package com.fansymasters.shoppinglist.list.details.usecase

import com.fansymasters.shoppinglist.list.domain.listdetails.ListDetailsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class DeleteListItemUseCase @Inject constructor(
    private val repository: ListDetailsRepository
) : DeleteListItemActions {

    override suspend fun deleteItem(itemId: Int) {
        repository.deleteItem(itemId)
    }
}
