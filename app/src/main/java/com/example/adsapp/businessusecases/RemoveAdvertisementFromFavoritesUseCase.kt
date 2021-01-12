package com.example.adsapp.business.usecases

import com.example.adsapp.database.AdsDao
import io.reactivex.Completable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoveAdvertisementFromFavoritesUseCase @Inject constructor(
    private val adsDao: AdsDao
) {

    operator fun invoke(id: Int) = remove(id)

    private fun remove(id: Int): Completable {
        return adsDao.deleteAdvertisement(id)
    }
}
