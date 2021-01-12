package com.example.adsapp.data.model


import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class Image(
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "height")
    val height: Int,
    @ColumnInfo(name = "width")
    val width: Int
)