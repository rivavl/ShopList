package com.marina.shoplist.data

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.util.Log

class ShopListProvider : ContentProvider() {


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
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val code = uriMatcher.match(uri)
        when (code) {
            GET_SHOP_ITEMS_QUERY -> {

            }
        }
        Log.d("ShopListProvider", "query $uri code $code")
        return null
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
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