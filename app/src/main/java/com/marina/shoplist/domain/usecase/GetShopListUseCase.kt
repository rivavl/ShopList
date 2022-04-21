package com.marina.shoplist.domain.usecase

import androidx.lifecycle.LiveData
import com.marina.shoplist.domain.entity.ShopItem
import com.marina.shoplist.domain.repository.ShopListRepository

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopList(): LiveData<List<ShopItem>> {
        return shopListRepository.getShopList()
    }
}