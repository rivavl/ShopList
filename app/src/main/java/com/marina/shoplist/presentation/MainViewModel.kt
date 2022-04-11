package com.marina.shoplist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.marina.shoplist.data.ShopListRepositoryImpl
import com.marina.shoplist.domain.EditShopItemUseCase
import com.marina.shoplist.domain.GetShopListUseCase
import com.marina.shoplist.domain.RemoveShopItemUseCase
import com.marina.shoplist.domain.ShopItem
import kotlinx.coroutines.launch

// if we need context -> AndroidViewModel()
// if not -> ViewModel()
class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val removeShopItemUseCase = RemoveShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun removeShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
            removeShopItemUseCase.removeShopItem(shopItem)
        }
    }

    fun changeEnableState(shopItem: ShopItem) {
        viewModelScope.launch {
            val newItem = shopItem.copy(enabled = !shopItem.enabled)
            editShopItemUseCase.editShopItem(newItem)
        }
    }
}
