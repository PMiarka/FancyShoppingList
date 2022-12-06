package com.fansymasters.shoppinglist.searchuser.di

import com.fansymasters.shoppinglist.searchuser.data.UsersRepositoryImpl
import com.fansymasters.shoppinglist.searchuser.domain.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface SearchUserSingletonModule {
    @Binds
    fun bindsSearchUserRepository(impl: UsersRepositoryImpl): UsersRepository
}
