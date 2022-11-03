package com.fansymasters.shoppinglist

import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class NavigationManager @Inject constructor() : NavigationReader, NavigationWriter {
    override val state = MutableSharedFlow<NavigationCommand>(extraBufferCapacity = 2)

    override fun navigate(path: String) {
        state.tryEmit(NavigationCommand.Route(path))
    }

    override fun navigateUp() {
        state.tryEmit(NavigationCommand.NavigateUp)
    }
}

sealed interface NavigationCommand {
    data class Route(val route: String) : NavigationCommand
    object NavigateUp : NavigationCommand
}