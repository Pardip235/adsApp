package com.example.adsapp.ui.advertisement

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.adsapp.AdsApplication
import com.example.adsapp.R

class FavoriteAdsFragment : BaseAdvertisementFragment() {

    override val shouldFetchFavoritesOnly: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity?.application as AdsApplication).component.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(AdvertisementViewModel::class.java)
    }

    override fun AdvertisementContract.ViewState.Error.handleError() = Unit

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.setTitle(R.string.favourites)
    }
}
