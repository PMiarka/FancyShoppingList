package com.fansymasters.shoppinglist.ui

import androidx.navigation.NavBackStackEntry
import com.fansymasters.shoppinglist.ui.NavigationRoutes.Lists.Arguments.LIST_ID

sealed interface NavigationRoutes {
    object Authentication : NavigationRoutes {
        const val Root = "authentication"
        const val Login = "${Root}/login"
        const val Register = "${Root}/register"
    }

    object Lists : NavigationRoutes {
        const val Root = "lists"
        const val Overview = "$Root/Overview"
        const val Create = "$Root/Create"
        const val Details = "$Root/Details/{$LIST_ID}"

        object Arguments {
            const val LIST_ID = "listId"
        }
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
