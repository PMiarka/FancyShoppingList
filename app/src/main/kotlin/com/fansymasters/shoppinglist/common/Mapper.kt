package com.fansymasters.shoppinglist.common

interface Mapper<FROM, TO> {
    fun map(from: FROM): TO
}

fun <T> unitMapper() = object : Mapper<T, Unit> {
    override fun map(from: T) = Unit
}

// TODO get rid of
fun <T> noMapper() = object : Mapper<T, T> {
    override fun map(from: T) = from
}