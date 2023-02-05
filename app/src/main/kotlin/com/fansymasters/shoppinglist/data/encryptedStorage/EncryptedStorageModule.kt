package com.fansymasters.shoppinglist.data.encryptedStorage

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class EncryptedStorageModule {
    @Provides
    fun providesEncryptedStorage(@ApplicationContext context: Context): EncryptedStorage =
        EncryptedStorageImpl(context)
}
