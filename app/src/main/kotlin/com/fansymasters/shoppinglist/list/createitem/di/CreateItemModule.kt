package com.fansymasters.shoppinglist.list.createitem.di

import com.fansymasters.shoppinglist.domain.ProcessingStateReader
import com.fansymasters.shoppinglist.list.createitem.data.CreateListItemRepositoryImpl
import com.fansymasters.shoppinglist.list.createitem.domain.CreateListItemRepository
import com.fansymasters.shoppinglist.list.createitem.usecase.CreateItemActions
import com.fansymasters.shoppinglist.list.createitem.usecase.CreateItemUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface CreateItemModule {

    @Binds
    fun bindsCreateItemActions(impl: CreateItemUseCase): CreateItemActions

    @Binds
    fun bindsCreateItemRepository(impl: CreateListItemRepositoryImpl): CreateListItemRepository
}
