package com.fansymasters.shoppinglist

import kotlinx.coroutines.flow.SharedFlow

internal interface NavigationReader {
    val navigationState: SharedFlow<NavigationCommand>
}