package com.example.adsapp.di.module


import android.content.Context
import androidx.room.Room
import com.example.adsapp.database.AdvertisementDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    companion object {
        const val DATABASE_NAME = "advertisement"
    }

    @Singleton
    @Provides
    fun provideAdvertisementDB(context: Context): AdvertisementDB {
        return Room.databaseBuilder(context, AdvertisementDB::class.java, DATABASE_NAME).build()
    }

    @Singleton
    @Provides
    fun provideAdsDao(database: AdvertisementDB) = database.adsDao()
}

