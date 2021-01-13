package com.example.adsapp.ui.advertisement

import androidx.lifecycle.MutableLiveData
import com.example.adsapp.data.model.Advertisement
import com.example.adsapp.utils.ErrorType

interface AdvertisementContract {

    sealed class ViewState() {
        data class ResultData(val items: List<Advertisement>): ViewState()
        object Empty : ViewState()
        data class Error(val errorType: ErrorType): ViewState()
    }

    interface ViewModel {
        val viewState: MutableLiveData<ViewState>
        fun addRemoveFromFavorites(id: Int)
        fun getAdvertisements(filterFavorites: Boolean)
    }
}
