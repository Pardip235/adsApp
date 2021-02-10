package com.example.adsapp.ui.advertisement

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.adsapp.AdsApplication
import com.example.adsapp.R
import com.example.adsapp.ui.advertisement.AdvertisementContract.ViewState
import com.example.adsapp.utils.ErrorType


class NewAdsFragment : BaseAdvertisementFragment() {

    override val shouldFetchFavoritesOnly: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity?.application as AdsApplication).component.inject(this)
        super.onCreate(savedInstanceState)
    }


    override fun ViewState.Error.handleError() {
        with(layoutHolder) {
            loadingTextView.text = requireContext().getText(errorType.getErrorMessage())
            loadingTextView.isVisible = true
            recyclerView.isVisible = false
            progressBar.isVisible = false
        }
    }


    @StringRes
    private fun ErrorType.getErrorMessage(): Int {
        return when (this) {
            ErrorType.NETWORK_ERROR -> R.string.network_error
            ErrorType.SERVER_ERROR -> R.string.server_error
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.setTitle(R.string.recent)
    }
}
