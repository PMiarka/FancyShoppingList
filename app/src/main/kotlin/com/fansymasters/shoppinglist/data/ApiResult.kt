package com.fansymasters.shoppinglist.data

import com.fansymasters.shoppinglist.domain.FancyError

sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val error: FancyError) : ApiResult<Nothing>()
}

fun <T> ApiResult<T>.onSuccess(action: (T) -> Unit): ApiResult<T> {
    if (this is ApiResult.Success) action(data)
    return this
}

fun <T> ApiResult<T>.onError(action: (FancyError) -> Unit): ApiResult<T> {
    if (this is ApiResult.Error) action(error)
    return this
}

