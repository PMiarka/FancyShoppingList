package com.fansymasters.shoppinglist.common

sealed interface GeneralError {
    object NoInternetConnection : GeneralError
    data class ServiceError(val e: Throwable) : GeneralError
}