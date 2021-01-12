package com.example.adsapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.adsapp.di.ViewModelFactory
import com.example.adsapp.di.ViewModelKey
import com.example.adsapp.ui.advertisement.AdvertisementViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AdvertisementViewModel::class)
    abstract fun AdvertisementViewModel.provideViewModel(): ViewModel
}
