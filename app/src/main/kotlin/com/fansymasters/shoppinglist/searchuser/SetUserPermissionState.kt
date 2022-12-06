package com.fansymasters.shoppinglist.searchuser

internal sealed interface SetUserPermissionState {
    object Idle : SetUserPermissionState
    class Show(val username: String) : SetUserPermissionState
}