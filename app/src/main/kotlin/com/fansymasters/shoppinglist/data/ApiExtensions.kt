package com.fansymasters.shoppinglist.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <FROM, TO> apiCall(
    mapper: (FROM) -> TO,
    function: suspend () -> FROM
): TO =
    withContext(Dispatchers.IO) {
        runCatching { function() }
            .map { mapper(it) }
            .getOrThrow()
    }
