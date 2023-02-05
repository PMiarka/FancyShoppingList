package com.fansymasters.shoppinglist.data.encryptedStorage

internal interface EncryptedStorage {
    fun getBoolean(key: String, defValue: Boolean): Boolean
    fun getString(key: String, defValue: String): String
    fun setBoolean(key: String, value: Boolean)
    fun setString(key: String, value: String)
}