package com.fansymasters.shoppinglist.common.di

import com.fansymasters.shoppinglist.common.commonprocessingstate.CommonProcessingHandler
import com.fansymasters.shoppinglist.common.commonprocessingstate.CommonProcessingStateReader
import com.fansymasters.shoppinglist.common.commonprocessingstate.CommonProcessingStateWriter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface CommonViewModelModule {
    @Binds
    fun bindsCommonProcessingStateReader(impl: CommonProcessingHandler): CommonProcessingStateReader

    @Binds
    fun bindsCommonProcessingStateWriter(impl: CommonProcessingHandler): CommonProcessingStateWriter
}
