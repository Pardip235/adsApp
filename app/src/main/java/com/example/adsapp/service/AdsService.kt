package com.example.adsapp.service

import com.example.adsapp.data.model.AdsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface AdsService {

    @GET("6a1bcc8f429dcdb8f9196e917e5138bd/raw/a542886ef057c91d822004ed15881f0c25221c18/discover.json")
    fun getAds(): Single<AdsResponse>
}