package com.fansymasters.shoppinglist.domain

sealed class FancyError {
    object NoInternetConnection : FancyError()
    object ClientError : FancyError()
    object ApiError : FancyError()
    object Unknown : FancyError()
}
