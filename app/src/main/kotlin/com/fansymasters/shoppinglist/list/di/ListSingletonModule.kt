package com.fansymasters.shoppinglist.list.di

import com.fansymasters.shoppinglist.list.data.ListDetailsRepositoryImpl
import com.fansymasters.shoppinglist.list.domain.ListDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class ListSingletonModule {

    @Provides
    fun providesLocalRepository(impl: ListDetailsRepositoryImpl): ListDetailsRepository = impl
}
