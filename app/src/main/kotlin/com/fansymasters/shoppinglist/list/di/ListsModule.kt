package com.fansymasters.shoppinglist.list.di

import com.fansymasters.shoppinglist.data.lists.ListDetailsDto
import com.fansymasters.shoppinglist.data.lists.ListDto
import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import com.fansymasters.shoppinglist.list.createlist.usecase.CreateListActions
import com.fansymasters.shoppinglist.list.createlist.usecase.CreateListUserCase
import com.fansymasters.shoppinglist.list.details.usecase.FetchListDetailsActions
import com.fansymasters.shoppinglist.list.details.usecase.FetchListDetailsUseCase
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

    @Provides
    fun provideFetchListDetailsActions(impl: FetchListDetailsUseCase): FetchListDetailsActions =
        impl

    @Provides
    fun providesFetchListDetailsState(impl: FetchListDetailsUseCase):
            ProcessingStateReader<ListDetailsDto> = impl

    @Provides
    fun providesListNavigation(impl: ListNavigationImpl): ListsNavigation = impl
}