package com.marina.shoplist.data

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.marina.shoplist.data.database.ShopListDao
import com.marina.shoplist.data.mapper.ShopListMapper
import com.marina.shoplist.domain.entity.ShopItem
import com.marina.shoplist.presentation.ShopApplication
import javax.inject.Inject

class ShopListProvider : ContentProvider() {

    private val component by lazy {
        (context as ShopApplication).component
    }

    @Inject
    lateinit var shopListDao: ShopListDao

    @Inject
    lateinit var mapper: ShopListMapper


    // используется для проверки uri в query()
    // # - любое число
    // * - любая строка
    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(
            "com.marina.shoplist",
            "shop_items",
            GET_SHOP_ITEMS_QUERY
        )
        addURI(
            "com.marina.shoplist",
            "shop_items/#",
            GET_SHOP_ITEM_BY_ID
        )
    }

    override fun onCreate(): Boolean {
        component.inject(this)
        return true
    }


    // объект Cursor позволяет читать данные
    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return when (uriMatcher.match(uri)) {
            GET_SHOP_ITEMS_QUERY -> {
                shopListDao.getShopListCursor()
            }
            else -> {
                null
            }
        }
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        when (uriMatcher.match(uri)) {
            GET_SHOP_ITEMS_QUERY -> {
                if (values == null) return null
                val id = values.getAsInteger("id")
                val name = values.getAsString("name")
                val count = values.getAsInteger("count")
                val enabled = values.getAsBoolean("enabled")

                val shopItem = ShopItem(
                    id = id,
                    name = name,
                    count = count,
                    enabled = enabled
                )
                shopListDao.addShopItemSync(mapper.mapEntityToDbModel(shopItem))
            }
        }
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(uri: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    companion object {
        private const val GET_SHOP_ITEMS_QUERY = 100
        private const val GET_SHOP_ITEM_BY_ID = 101
    }
}