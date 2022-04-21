package com.marina.shoplist.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.marina.shoplist.data.database.AppDatabase
import com.marina.shoplist.data.mapper.ShopListMapper
import com.marina.shoplist.domain.entity.ShopItem
import com.marina.shoplist.domain.repository.ShopListRepository

class ShopListRepositoryImpl(
    application: Application
) : ShopListRepository {

    private val shopListDao = AppDatabase.getInstance(application).shopListDao()
    private val mapper = ShopListMapper()

    override suspend fun addShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun editShopItem(shopItem: ShopItem) =
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))

    override suspend fun getShopItem(shopItemId: Int): ShopItem {
        val dbModel = shopListDao.getShopItem(shopItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getShopList(): LiveData<List<ShopItem>> =
        Transformations.map(shopListDao.getShopList()) {
            mapper.mapListDbModelToListEntity(it)
        }

    override suspend fun removeShopItem(shopItem: ShopItem) {
        shopListDao.deleteItem(shopItem.id)
    }
}