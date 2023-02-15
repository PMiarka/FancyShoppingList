package com.fansymasters.shoppinglist.list.details.usecase

import com.fansymasters.shoppinglist.list.domain.listdetails.ListDetailsLocalStorageReader
import com.fansymasters.shoppinglist.list.domain.listdetails.ListDetailsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class FetchListDetailsUseCase @Inject constructor(
    private val repository: ListDetailsRepository,
    localStorageReader: ListDetailsLocalStorageReader
) : FetchListDetailsActions {
    override val state = localStorageReader.localState

    override suspend fun fetchListDetails(listId: Int) {
        repository.fetchListItems(listId)
    }
}