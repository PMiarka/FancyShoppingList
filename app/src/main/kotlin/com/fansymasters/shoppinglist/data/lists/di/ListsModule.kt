package com.fansymasters.shoppinglist.data.lists.di

import com.fansymasters.shoppinglist.data.di.Authenticated
import com.fansymasters.shoppinglist.data.lists.ListsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal class ListsModule {

    @Provides
    fun providesListsApi(
        @Authenticated retrofit: Retrofit
    ): ListsApi = retrofit.create(ListsApi::class.java)
}