package com.fansymasters.shoppinglist.common

import kotlinx.coroutines.flow.MutableSharedFlow
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class GeneralErrorHandlerImpl @Inject constructor() : GeneralErrorHandler {
    override val generaleErrorState = MutableSharedFlow<GeneralError>(extraBufferCapacity = 1)

    override fun mapError(e: Throwable) {
        val error = when (e) {
            is UnknownHostException -> GeneralError.NoInternetConnection
            else -> GeneralError.ServiceError(e)
        }

        generaleErrorState.tryEmit(error)
    }
}