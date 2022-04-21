package com.marina.shoplist.di

import android.app.Application
import com.marina.shoplist.data.database.AppDatabase
import com.marina.shoplist.data.database.ShopListDao
import com.marina.shoplist.data.repository.ShopListRepositoryImpl
import com.marina.shoplist.domain.repository.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindShopListRepository(impl: ShopListRepositoryImpl): ShopListRepository

    companion object {
        @ApplicationScope
        @Provides
        fun provideShopListDao(application: Application): ShopListDao {
            return AppDatabase.getInstance(application).shopListDao()
        }
    }
}