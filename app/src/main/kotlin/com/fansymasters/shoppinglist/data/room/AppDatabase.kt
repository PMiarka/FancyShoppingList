package com.fansymasters.shoppinglist.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ListItemLocalDto::class, ListItemDeletedDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun listItemDao(): ListItemsLocalDao
    abstract fun listItemsDeletedDao(): ListItemsDeletedDao
}