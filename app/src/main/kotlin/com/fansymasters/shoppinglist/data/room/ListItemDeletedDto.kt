package com.fansymasters.shoppinglist.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ListItemDeletedDto(@PrimaryKey val id: Int)