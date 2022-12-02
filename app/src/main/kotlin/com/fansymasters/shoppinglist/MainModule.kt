package com.fansymasters.shoppinglist

import com.fansymasters.shoppinglist.presentation.UiEventStateReader
import com.fansymasters.shoppinglist.presentation.UiEventStateWriter
import com.fansymasters.shoppinglist.presentation.UiEventUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface MainModule {

    @Binds
    fun bindsNavigationReader(impl: NavigationManager): NavigationReader

    @Binds
    fun bindsNavigationWriter(impl: NavigationManager): NavigationWriter

    @Binds
    fun bindsUiEventReader(impl: UiEventUseCase): UiEventStateReader

    @Binds
    fun bindsUiEventWriter(impl: UiEventUseCase): UiEventStateWriter
}
