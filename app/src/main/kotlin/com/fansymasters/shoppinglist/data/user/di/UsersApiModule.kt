package com.fansymasters.shoppinglist.data.user.di

import com.fansymasters.shoppinglist.data.di.Authenticated
import com.fansymasters.shoppinglist.data.user.UsersApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal class UsersApiModule {

    @Provides
    fun providesUsersApi(
        @Authenticated retrofit: Retrofit
    ): UsersApi = retrofit.create(UsersApi::class.java)
}