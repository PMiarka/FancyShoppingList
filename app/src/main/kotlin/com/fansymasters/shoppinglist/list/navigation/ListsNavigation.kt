package com.fansymasters.shoppinglist.list.navigation

internal interface ListsNavigation {
    fun openCreateList()
    fun openListsOverview()
    fun openListDetails(listId: Int)
    fun openCreateOrUpdateItem(listId: Int, itemId: Int?)
    fun navigateUp()
}