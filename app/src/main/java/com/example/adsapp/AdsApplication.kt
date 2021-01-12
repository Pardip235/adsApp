package com.example.adsapp

import android.app.Application
import com.example.adsapp.di.component.AppComponent
import com.example.adsapp.di.component.DaggerAppComponent


class AdsApplication : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().bindContext(this)
            .build()
    }
}
