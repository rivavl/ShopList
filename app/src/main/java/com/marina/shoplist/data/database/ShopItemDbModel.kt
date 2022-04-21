package com.marina.shoplist.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shop_items")
data class ShopItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    var count: Int,
    var enabled: Boolean,
)