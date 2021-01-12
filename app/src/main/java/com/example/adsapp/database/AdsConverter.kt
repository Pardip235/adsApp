package com.example.adsapp.database

import androidx.room.TypeConverter
import com.example.adsapp.data.model.Image
import com.example.adsapp.data.model.Price
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AdsConverter {

    @TypeConverter
    fun fromImage(image: Image?): String? {
        return if (image == null) null else Gson().toJson(image)
    }

    @TypeConverter
    fun toImage(value: String?): Image? {
        return if (value == null)
            null
        else
            Gson().fromJson<Image>(value, object : TypeToken<Image>() {}.type)
    }

    @TypeConverter
    fun fromPrice(price: Price?): String? {
        return if (price == null) null else Gson().toJson(price)
    }

    @TypeConverter
    fun toPrice(value: String?): Price? {
        return if (value == null)
            null
        else
            Gson().fromJson<Price>(value, object : TypeToken<Price>() {}.type)
    }
}