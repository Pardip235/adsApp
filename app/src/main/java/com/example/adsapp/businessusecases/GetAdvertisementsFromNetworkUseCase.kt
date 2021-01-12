package com.example.adsapp.business.usecases

import com.example.adsapp.data.model.Advertisement
import com.example.adsapp.database.AdsDao
import com.example.adsapp.service.AdsService
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAdvertisementsFromNetworkUseCase @Inject constructor(
    private val adsDao: AdsDao,
    private val adsService: AdsService
) {

    operator fun invoke() = get()

    private fun get() : Single<List<Advertisement>> {
        return adsService.getAds()
            .map { it.items }
            .map { items ->
                items.map {
                    it.likeStatus = adsDao.isFavorite(it.id)
                    it
                }
            }
    }
}
