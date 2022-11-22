package com.fansymasters.shoppinglist.common

interface Mapper<FROM, TO> {
    fun map(from: FROM): TO
}