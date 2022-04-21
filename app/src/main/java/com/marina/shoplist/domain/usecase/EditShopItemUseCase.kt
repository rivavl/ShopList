package com.marina.shoplist.domain.usecase

import com.marina.shoplist.domain.entity.ShopItem
import com.marina.shoplist.domain.repository.ShopListRepository

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun editShopItem(shopItem: ShopItem) {
        shopListRepository.editShopItem(shopItem)
    }
}