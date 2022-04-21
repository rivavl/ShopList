package com.marina.shoplist.di

import android.app.Application
import androidx.fragment.app.Fragment
import com.marina.shoplist.presentation.activities.MainActivity
import com.marina.shoplist.presentation.activities.ShopItemActivity
import com.marina.shoplist.presentation.fragments.ShopItemFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: ShopItemFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}