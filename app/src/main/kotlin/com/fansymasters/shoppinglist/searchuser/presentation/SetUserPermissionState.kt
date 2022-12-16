package com.fansymasters.shoppinglist.searchuser.presentation

internal sealed interface SetUserPermissionState {
    object Idle : SetUserPermissionState
    class Show(val username: String) : SetUserPermissionState
}