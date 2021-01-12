package com.example.adsapp.business.usecases

import com.example.adsapp.database.AdsDao
import com.example.adsapp.data.model.Advertisement
import io.reactivex.Completable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddAdvertisementToFavoriteUseCase @Inject constructor(
    private val adsDao: AdsDao
) {

    operator fun invoke(advertisement: Advertisement) = add(advertisement)

    private fun add(advertisement: Advertisement): Completable {
        return adsDao.insertAdvertisementItem(advertisement)
    }
}
