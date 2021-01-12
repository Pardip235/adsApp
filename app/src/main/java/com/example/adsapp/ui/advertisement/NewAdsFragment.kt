package com.example.adsapp.ui.advertisement

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.adsapp.AdsApplication
import com.example.adsapp.R


class NewAdsFragment : BaseAdvertisementFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity?.application as AdsApplication).component.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.setTitle(R.string.favourites)
    }
}
