package com.fansymasters.shoppinglist.data.room

import com.fansymasters.shoppinglist.common.Mapper
import com.fansymasters.shoppinglist.data.lists.ListDetailsDto
import com.fansymasters.shoppinglist.data.lists.ListItemDto
import com.fansymasters.shoppinglist.data.lists.ShopUsersDto
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface LocalMappersModule {

    @Binds
    fun bindsShopUserMapper(impl: ShopUserMapper): Mapper<ShopUsersDto, ShopUsersLocalDto>

    @Binds
    fun bindsShopUserRequestMapper(impl: ShopUserRequestMapper): Mapper<ShopUsersLocalDto, ShopUsersDto>

    @Binds
    fun bindsListItemMapper(impl: ListItemMapper): Mapper<ListItemDto, ListItemLocalDto>

    @Binds
    fun bindsListDetailsLocalMapper(impl: ListDetailsLocalMapper): Mapper<ListDetailsDto, ListDetailsLocalDto>
}
