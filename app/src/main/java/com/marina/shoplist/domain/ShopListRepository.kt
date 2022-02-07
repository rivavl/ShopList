package com.marina.shoplist.domain

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getShopItem(shopItemId: Int): ShopItem

    fun getShopList(): List<ShopItem>

    fun removeShopItem(shopItem: ShopItem)
}