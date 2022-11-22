package com.fansymasters.shoppinglist.list.di

import android.content.Context
import androidx.room.Room
import com.fansymasters.shoppinglist.common.Mapper
import com.fansymasters.shoppinglist.data.lists.ListItemDto
import com.fansymasters.shoppinglist.data.room.AppDatabase
import com.fansymasters.shoppinglist.data.room.ListItemLocalDto
import com.fansymasters.shoppinglist.data.room.ListItemsDeletedDao
import com.fansymasters.shoppinglist.data.room.ListItemsLocalDao
import com.fansymasters.shoppinglist.list.data.ListDetailsRepositoryImpl
import com.fansymasters.shoppinglist.list.domain.ListDetailsRepository
import com.fansymasters.shoppinglist.list.domain.ListItemDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class ListSingletonModule {

    @Provides
    fun providesLocalRepository(impl: ListDetailsRepositoryImpl): ListDetailsRepository = impl

    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "fancy-database"
        ).build()

    @Provides
    fun providesListItemDao(appDatabase: AppDatabase): ListItemsLocalDao = appDatabase.listItemDao()

    @Provides
    fun providesListItemsDeletedDao(appDatabase: AppDatabase): ListItemsDeletedDao =
        appDatabase.listItemsDeletedDao()

    @Provides
    fun providesListItemLocalDtoMapper(impl: ListItemDtoMapper):
            Mapper<ListItemDto, ListItemLocalDto> = impl
}
