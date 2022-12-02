package com.fansymasters.shoppinglist

import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class NavigationManager @Inject constructor() : NavigationReader, NavigationWriter {
    override val navigationState = MutableSharedFlow<NavigationCommand>(extraBufferCapacity = 2)

    override fun navigate(path: String) {
        navigationState.tryEmit(NavigationCommand.Route(path))
    }

    override fun navigateUp() {
        navigationState.tryEmit(NavigationCommand.NavigateUp)
    }
}

sealed interface NavigationCommand {
    data class Route(val route: String) : NavigationCommand
    object NavigateUp : NavigationCommand
}