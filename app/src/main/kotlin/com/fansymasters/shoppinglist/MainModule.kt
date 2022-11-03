package com.fansymasters.shoppinglist

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
}
