package com.fansymasters.shoppinglist.searchuser.domain

enum class PermissionType {
    OWNER, EDIT, READ_ONLY
}

fun PermissionType.toDisplayText() = when (this) {
    PermissionType.OWNER -> "Owner"
    PermissionType.EDIT -> "Edit"
    PermissionType.READ_ONLY -> "Read only"
}

fun PermissionType.toApiKey() = when (this) {
    PermissionType.OWNER -> "Owner"
    PermissionType.EDIT -> "Edit"
    PermissionType.READ_ONLY -> "ReadOnly"
}