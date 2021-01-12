package com.example.adsapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "advertisementTable")
data class Advertisement(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "image")
    val image: Image,
    @ColumnInfo(name = "price")
    val price: Price?,
    @ColumnInfo(name = "location")
    val location: String?,
    @ColumnInfo(name = "likeStatus")
    var likeStatus: Boolean = false
)
