package com.fansymasters.shoppinglist.common

fun interface Mapper<FROM, TO> {
    fun map(from: FROM): TO
}

fun unitMapper(): (Any) -> Unit = { }

// TODO get rid of
fun <T> noMapper(): (T) -> T = { from -> from }