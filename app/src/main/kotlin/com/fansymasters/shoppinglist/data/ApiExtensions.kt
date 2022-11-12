package com.fansymasters.shoppinglist.data

import com.fansymasters.shoppinglist.domain.FancyError
import retrofit2.HttpException
import java.net.UnknownHostException

suspend fun <T> apiCall(function: suspend () -> T): ApiResult<T> =
    runCatching { function() }
        .map { ApiResult.Success(it) }
        .onFailure { ApiResult.Error(FancyError.Unknown) }
        .getOrElse { ApiResult.Error(it.mapToError()) }

private fun Throwable.mapToError(): FancyError =
    when {
        this is HttpException && this.code() > 500 -> FancyError.ApiError
        this is HttpException && this.code() > 400 && this.code() < 500 -> FancyError.ClientError
        this is UnknownHostException -> FancyError.NoInternetConnection
        else -> FancyError.Unknown
    }
