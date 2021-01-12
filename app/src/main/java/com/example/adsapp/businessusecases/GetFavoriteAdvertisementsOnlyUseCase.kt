package com.example.adsapp.business.usecases

import com.example.adsapp.database.AdsDao
import com.example.adsapp.data.model.Advertisement
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetFavoriteAdvertisementsOnlyUseCase @Inject constructor(
    private val adsDao: AdsDao
) {

    operator fun invoke() = get()

    private fun get(): Flowable<List<Advertisement>> {
        return adsDao.getFavourites()
    }
}

