package com.marina.shoplist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marina.shoplist.data.ShopListRepositoryImpl
import com.marina.shoplist.domain.EditShopItemUseCase
import com.marina.shoplist.domain.GetShopListUseCase
import com.marina.shoplist.domain.RemoveShopItemUseCase
import com.marina.shoplist.domain.ShopItem

// if we need context -> AndroidViewModel()
// if not -> ViewModel()
class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val removeShopItemUseCase = RemoveShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = MutableLiveData<List<ShopItem>>()

    fun getShopList() {
        val list = getShopListUseCase.getShopList()

        //value - only in main thread
        //post - in any thread
        shopList.value = list
    }

    fun removeShopItem(shopItem: ShopItem) {
        removeShopItemUseCase.removeShopItem(shopItem)
        getShopList()
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
        getShopList()
    }
}