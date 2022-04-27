package com.marina.shoplist.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marina.shoplist.domain.entity.ShopItem
import com.marina.shoplist.domain.usecase.EditShopItemUseCase
import com.marina.shoplist.domain.usecase.GetShopListUseCase
import com.marina.shoplist.domain.usecase.RemoveShopItemUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

// if we need context -> AndroidViewModel()
// if not -> ViewModel()
class MainViewModel @Inject constructor(
    private val getShopListUseCase: GetShopListUseCase,
    private val removeShopItemUseCase: RemoveShopItemUseCase,
    private val editShopItemUseCase: EditShopItemUseCase
) : ViewModel() {

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
