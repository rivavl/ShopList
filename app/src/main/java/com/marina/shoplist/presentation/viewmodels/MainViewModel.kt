package com.marina.shoplist.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.marina.shoplist.data.repository.ShopListRepositoryImpl
import com.marina.shoplist.domain.usecase.EditShopItemUseCase
import com.marina.shoplist.domain.usecase.GetShopListUseCase
import com.marina.shoplist.domain.usecase.RemoveShopItemUseCase
import com.marina.shoplist.domain.entity.ShopItem
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
