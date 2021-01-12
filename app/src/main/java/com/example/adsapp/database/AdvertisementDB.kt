package com.example.adsapp.database


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.adsapp.data.model.Advertisement

@Database(entities = [(Advertisement::class)], version = 1, exportSchema = false)
@TypeConverters(AdsConverter::class)
abstract class AdvertisementDB : RoomDatabase() {
    abstract fun adsDao(): AdsDao
}