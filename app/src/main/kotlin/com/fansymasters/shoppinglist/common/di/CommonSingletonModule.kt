package com.fansymasters.shoppinglist.common.di

import com.fansymasters.shoppinglist.common.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface CommonSingletonModule {

    @Binds
    fun bindsCurrentSessionRepository(impl: CurrentSessionRepositoryImpl): CurrentSessionRepository

    @Binds
    fun bindsProgressHandler(impl: ProgressHandlerImpl): ProgressHandler

    @Binds
    fun bindsGeneralErrorHandler(impl: GeneralErrorHandlerImpl): GeneralErrorHandler
}
