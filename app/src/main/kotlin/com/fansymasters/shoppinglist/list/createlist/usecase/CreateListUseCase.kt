package com.fansymasters.shoppinglist.list.createlist.usecase

import com.fansymasters.shoppinglist.list.domain.lists.ListsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class CreateListUseCase @Inject constructor(
    private val repository: ListsRepository
) : CreateListActions {
    override suspend fun createList(name: String, description: String) {
        repository.createList(name = name, description = description)
    }
}