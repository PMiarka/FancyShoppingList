package com.fansymasters.shoppinglist.searchuser.di

import com.fansymasters.shoppinglist.domain.StateReader
import com.fansymasters.shoppinglist.searchuser.domain.UserDomainDto
import com.fansymasters.shoppinglist.searchuser.navigation.SearchUserNavigation
import com.fansymasters.shoppinglist.searchuser.navigation.SearchUserNavigationImpl
import com.fansymasters.shoppinglist.searchuser.usecase.SearchUserActions
import com.fansymasters.shoppinglist.searchuser.usecase.SearchUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal class SearchUserModule {
    @Provides
    fun bindsSearchUserActions(impl: SearchUserUseCase): SearchUserActions = impl

    @Provides
    fun bindsSearchUserStateReader(impl: SearchUserUseCase): StateReader<List<UserDomainDto>> =
        impl

    @Provides
    fun bindsSearchUserNavigation(impl: SearchUserNavigationImpl): SearchUserNavigation = impl
}
