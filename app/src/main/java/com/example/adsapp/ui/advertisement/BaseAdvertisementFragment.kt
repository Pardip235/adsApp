package com.example.adsapp.ui.advertisement


import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.adsapp.R
import javax.inject.Inject


abstract class BaseAdvertisementFragment :  Fragment(R.layout.fragment_adlist) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
}
