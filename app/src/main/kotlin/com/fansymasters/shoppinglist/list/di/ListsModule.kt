package com.fansymasters.shoppinglist.list.di

import com.fansymasters.shoppinglist.data.lists.ListDto
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import com.fansymasters.shoppinglist.list.usecase.CreateListActions
import com.fansymasters.shoppinglist.list.usecase.CreateListUserCase
import com.fansymasters.shoppinglist.list.usecase.FetchListsActions
import com.fansymasters.shoppinglist.list.usecase.FetchListsUserCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal class ListsModule {

    @Provides
    fun providesFetchListsActions(impl: FetchListsUserCase): FetchListsActions = impl

    @Provides
    fun providesFetchListsState(impl: FetchListsUserCase):
            ProcessingStateReader<List<ListDto>> = impl

    @Provides
    fun providesCreateListActions(impl: CreateListUserCase): CreateListActions = impl

    @Provides
    fun providesCreateListState(impl: CreateListUserCase):
            ProcessingStateReader<ListDto> = impl
}