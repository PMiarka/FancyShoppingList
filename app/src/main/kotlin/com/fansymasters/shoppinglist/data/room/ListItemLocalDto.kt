package com.fansymasters.shoppinglist.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ListItemLocalDto(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "qty") val qty: Int,
    @ColumnInfo(name = "sortNo") val sortNo: Int,
    @ColumnInfo(name = "unit") val unit: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "finished") val finished: Boolean,
    @ColumnInfo(name = "deleted") val deleted: Boolean,
)
