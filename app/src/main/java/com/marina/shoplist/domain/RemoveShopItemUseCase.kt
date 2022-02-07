package com.marina.shoplist.domain

class RemoveShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun removeShopItem(shopItemId: Int) {
        shopListRepository.removeShopItem(shopItemId)
    }
}