package com.fansymasters.shoppinglist.list.di

import com.fansymasters.shoppinglist.common.Mapper
import com.fansymasters.shoppinglist.data.lists.ListDto
import com.fansymasters.shoppinglist.list.data.listdetails.ListDetailsLocalStorageImpl
import com.fansymasters.shoppinglist.list.data.listdetails.ListDetailsLocalStorageWriter
import com.fansymasters.shoppinglist.list.data.listdetails.ListDetailsRepositoryImpl
import com.fansymasters.shoppinglist.list.data.lists.ListDtoMapper
import com.fansymasters.shoppinglist.list.data.lists.ListsLocalStorageImpl
import com.fansymasters.shoppinglist.list.data.lists.ListsLocalStorageWriter
import com.fansymasters.shoppinglist.list.data.lists.ListsRepositoryImpl
import com.fansymasters.shoppinglist.list.domain.listdetails.ListDetailsLocalStorageReader
import com.fansymasters.shoppinglist.list.domain.listdetails.ListDetailsRepository
import com.fansymasters.shoppinglist.list.domain.lists.ListLocalDto
import com.fansymasters.shoppinglist.list.domain.lists.ListsLocalStorageReader
import com.fansymasters.shoppinglist.list.domain.lists.ListsRepository
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
    fun bindsListDetailsLocalStorageWriter(impl: ListDetailsLocalStorageImpl): ListDetailsLocalStorageWriter

    @Binds
    fun bindsListDetailsLocalStorageReader(impl: ListDetailsLocalStorageImpl): ListDetailsLocalStorageReader

    @Binds
    fun bindsListsLocalRepository(impl: ListsRepositoryImpl): ListsRepository

    @Binds
    fun bindsListsLocalStorageWriter(impl: ListsLocalStorageImpl): ListsLocalStorageWriter

    @Binds
    fun bindsListsLocalStorageReader(impl: ListsLocalStorageImpl): ListsLocalStorageReader

    @Binds
    fun bindsListDtoMapper(impl: ListDtoMapper): Mapper<ListDto, ListLocalDto>
}

