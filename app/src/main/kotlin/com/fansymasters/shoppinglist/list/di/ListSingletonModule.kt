package com.fansymasters.shoppinglist.list.di

import com.fansymasters.shoppinglist.common.Mapper
import com.fansymasters.shoppinglist.data.lists.ListDto
import com.fansymasters.shoppinglist.list.data.*
import com.fansymasters.shoppinglist.list.domain.ListDetailsRepository
import com.fansymasters.shoppinglist.list.domain.ListLocalDto
import com.fansymasters.shoppinglist.list.domain.ListsLocalStorageReader
import com.fansymasters.shoppinglist.list.domain.ListsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface ListSingletonModule {

    @Binds
    fun bindsListDetailsLocalRepository(impl: ListDetailsRepositoryImpl): ListDetailsRepository

    @Binds
    fun bindsListsLocalRepository(impl: ListsRepositoryImpl): ListsRepository

    @Binds
    fun bindsListsLocalStorageWriter(impl: ListsLocalStorageImpl): ListsLocalStorageWriter

    @Binds
    fun bindsListsLocalStorageReader(impl: ListsLocalStorageImpl): ListsLocalStorageReader

    @Binds
    fun bindsListDtoMapper(impl: ListDtoMapper): Mapper<ListDto, ListLocalDto>
}

