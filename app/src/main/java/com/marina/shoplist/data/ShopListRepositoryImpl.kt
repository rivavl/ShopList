package com.marina.shoplist.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import com.marina.shoplist.domain.ShopItem
import com.marina.shoplist.domain.ShopListRepository

class ShopListRepositoryImpl(
    application: Application
) : ShopListRepository {

    private val shopListDao = AppDatabase.getInstance(application).shopListDao()
    private val mapper = ShopListMapper()

    override fun addShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override fun editShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        val dbModel = shopListDao.getShopItem(shopItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getShopList(): LiveData<List<ShopItem>> =
        Transformations.map(shopListDao.getShopList()) {
            mapper.mapListDbModelToListEntity(it)
        }

    override fun removeShopItem(shopItem: ShopItem) {
        shopListDao.deleteItem(shopItem.id)
    }
}