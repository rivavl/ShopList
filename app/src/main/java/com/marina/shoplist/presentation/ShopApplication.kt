package com.marina.shoplist.presentation

import android.app.Application
import com.marina.shoplist.di.DaggerApplicationComponent

class ShopApplication : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

}