package com.fansymasters.shoppinglist.data.user.di

import com.fansymasters.shoppinglist.common.Mapper
import com.fansymasters.shoppinglist.data.user.dto.UserRemoteMapper
import com.fansymasters.shoppinglist.searchuser.domain.UserDomainDto
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface UsersModule {

    @Binds
    fun bindsUserRemoteDto(impl: UserRemoteMapper): Mapper<String, UserDomainDto>
}
