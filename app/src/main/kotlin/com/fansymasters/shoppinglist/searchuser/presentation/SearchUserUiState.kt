package com.fansymasters.shoppinglist.searchuser.presentation

import com.fansymasters.shoppinglist.common.commonprocessingstate.CommonProcessingState
import com.fansymasters.shoppinglist.data.room.ShopUsersLocalDto
import com.fansymasters.shoppinglist.searchuser.domain.UserDomainDto

data class SearchUserUiState(
    val apiState: CommonProcessingState,
    val foundUsers: List<UserDomainDto>,
    val currentlySharedUsers: List<ShopUsersLocalDto>
)
