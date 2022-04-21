package com.marina.shoplist.domain.usecase

import com.marina.shoplist.domain.entity.ShopItem
import com.marina.shoplist.domain.repository.ShopListRepository

class RemoveShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun removeShopItem(shopItem: ShopItem) {
        shopListRepository.removeShopItem(shopItem)
    }
}