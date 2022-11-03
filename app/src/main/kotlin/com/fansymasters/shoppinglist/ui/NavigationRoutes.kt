package com.fansymasters.shoppinglist.ui

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
    }
}
