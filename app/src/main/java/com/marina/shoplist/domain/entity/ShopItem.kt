package com.marina.shoplist.domain.entity

data class ShopItem(
    var name: String,
    var count: Int,
    var enabled: Boolean,
    var id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}