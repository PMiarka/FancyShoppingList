package com.fansymasters.shoppinglist

internal interface NavigationWriter {
    fun navigate(path: String)
    fun navigateUp()
}