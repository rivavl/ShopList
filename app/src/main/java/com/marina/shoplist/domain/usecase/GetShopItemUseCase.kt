package com.marina.shoplist.domain.usecase

import com.marina.shoplist.domain.entity.ShopItem
import com.marina.shoplist.domain.repository.ShopListRepository

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun getShopItem(shopItemId: Int): ShopItem {
        return shopListRepository.getShopItem(shopItemId)
    }
}