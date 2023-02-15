package com.fansymasters.shoppinglist.ui

import androidx.navigation.NavBackStackEntry
import com.fansymasters.shoppinglist.ui.NavigationRoutes.CommonArguments.ITEM_ID
import com.fansymasters.shoppinglist.ui.NavigationRoutes.CommonArguments.LIST_ID

sealed interface NavigationRoutes {
    object Authentication : NavigationRoutes {
        const val Root = "authentication"
        const val Login = "${Root}/login"
        const val Register = "${Root}/register"
    }

    object Users : NavigationRoutes {
        const val Root = "users"
        const val SearchUser = "$Root/SearchUser/{$LIST_ID}"
    }

    object Lists : NavigationRoutes {
        const val Root = "lists"
        const val Overview = "$Root/Overview"
        const val Create = "$Root/Create"
        const val Details = "$Root/Details/{$LIST_ID}"
        const val CreateItem = "$Root/CreateItem/{$LIST_ID}/{$ITEM_ID}"

    }

    object CommonArguments {
        const val LIST_ID = "listId"
        const val ITEM_ID = "itemId"
        const val ITEM_ID_UNDEFINED = -1
    }
}

fun String.setStringArgument(key: String, value: String): String =
    this.replace("{$key}", value)

fun String.setIntArgument(key: String, value: Int): String =
    this.replace("{$key}", value.toString())

fun NavBackStackEntry.getStringArgument(key: String): String? =
    arguments?.getString(key)

fun NavBackStackEntry.getIntArgument(key: String): Int? =
    arguments?.getString(key)?.toInt()
