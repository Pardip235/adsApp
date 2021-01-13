package com.example.adsapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.adsapp.data.model.Advertisement
import io.reactivex.Completable
import io.reactivex.Flowable


@Dao
interface AdsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAdvertisementItem(advertisement: Advertisement): Completable

    @Query("DELETE FROM advertisementTable WHERE id = :id")
    fun deleteAdvertisement(id: Int): Completable

    @Query("SELECT * FROM advertisementTable ORDER BY id ASC")
    fun getFavourites(): Flowable<List<Advertisement>>

    @Query("SELECT EXISTS(SELECT * FROM advertisementTable WHERE id = :id)")
    fun isFavorite(id: Int): Boolean
}
