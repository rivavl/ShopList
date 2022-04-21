package com.marina.shoplist.domain.usecase

import com.marina.shoplist.domain.entity.ShopItem
import com.marina.shoplist.domain.repository.ShopListRepository

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun addShopItem(shopItem: ShopItem) {
        shopListRepository.addShopItem(shopItem)
    }
}