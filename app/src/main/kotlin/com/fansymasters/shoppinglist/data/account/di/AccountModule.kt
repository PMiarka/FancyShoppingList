package com.fansymasters.shoppinglist.data.account.di

import com.fansymasters.shoppinglist.data.account.AccountApi
import com.fansymasters.shoppinglist.data.di.NotAuthenticated
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal class AccountModule {

    @Provides
    fun providesAccountApi(
        @NotAuthenticated retrofit: Retrofit
    ): AccountApi = retrofit.create(AccountApi::class.java)
}