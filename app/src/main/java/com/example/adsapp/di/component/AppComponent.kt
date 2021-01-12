package com.example.adsapp.di.component

import android.content.Context
import com.example.adsapp.di.module.*
import com.example.adsapp.ui.advertisement.NewAdsFragment
import com.example.adsapp.ui.advertisement.FavoriteAdsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    abstract class Builder {
        @BindsInstance
        abstract fun bindContext(context: Context): Builder
        abstract fun build(): AppComponent
    }

    fun inject(newAdsFragment: NewAdsFragment)

    fun inject(favoriteAdsFragment: FavoriteAdsFragment)
}