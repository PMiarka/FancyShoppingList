package com.fansymasters.shoppinglist.list.di

import com.fansymasters.shoppinglist.data.lists.ListDto
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import com.fansymasters.shoppinglist.list.createlist.usecase.CreateListActions
import com.fansymasters.shoppinglist.list.createlist.usecase.CreateListUseCase
import com.fansymasters.shoppinglist.list.details.usecase.*
import com.fansymasters.shoppinglist.list.navigation.ListNavigationImpl
import com.fansymasters.shoppinglist.list.navigation.ListsNavigation
import com.fansymasters.shoppinglist.list.overview.usecase.FetchListsActions
import com.fansymasters.shoppinglist.list.overview.usecase.FetchListsUserCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal class ListsViewModelModule {

    @Provides
    fun providesFetchListsActions(impl: FetchListsUserCase): FetchListsActions = impl

    @Provides
    fun providesCreateListActions(impl: CreateListUseCase): CreateListActions = impl

    @Provides
    fun providesCreateListState(impl: CreateListUseCase):
            ProcessingStateReader<ListDto> = impl

    @Provides
    fun provideFetchListDetailsActions(impl: FetchListDetailsUseCase): FetchListDetailsActions =
        impl

    @Provides
    fun provideUpdateItemActions(impl: UpdateListItemUseCase): UpdateListItemActions = impl

    @Provides
    fun provideDeleteItemActions(impl: DeleteListItemUseCase): DeleteListItemActions = impl

    @Provides
    fun providesListNavigation(impl: ListNavigationImpl): ListsNavigation = impl
}