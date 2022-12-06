package com.fansymasters.shoppinglist.data

import com.fansymasters.shoppinglist.domain.FancyError
import retrofit2.HttpException
import java.net.UnknownHostException

suspend fun <FROM, TO> apiCall(
    mapper: (FROM) -> TO,
    function: suspend () -> FROM
): ApiResult<TO> =
    runCatching { function() }
        .map { ApiResult.Success(mapper(it)) }
        .onFailure { ApiResult.Error(it.mapToError()) }
        .getOrElse { ApiResult.Error(it.mapToError()) }

private fun Throwable.mapToError(): FancyError =
    when {
        this is HttpException && this.code() > 500 -> FancyError.ApiError
        this is HttpException && this.code() > 400 && this.code() < 500 -> FancyError.ClientError
        this is UnknownHostException -> FancyError.NoInternetConnection
        else -> FancyError.Unknown
    }

