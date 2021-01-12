package com.example.adsapp.ui.advertisement

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adsapp.utils.ErrorType
import com.example.adsapp.business.usecases.GetAdvertisementsFromNetworkUseCase
import com.example.adsapp.ui.advertisement.AdvertisementContract.ViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import javax.inject.Inject

class AdvertisementViewModel @Inject constructor(
    private val getAdvertisementsFromNetworkUseCase: GetAdvertisementsFromNetworkUseCase
) : ViewModel(), AdvertisementContract.ViewModel {

    private var addRemoveDisposable: Disposable? = null
    private var disposable: Disposable? = null

    override val viewState: MutableLiveData<ViewState> = MutableLiveData()

    override fun getAdvertisements(filterFavorites: Boolean) {
        if (!filterFavorites) {
            fetchAds()
        } else {
           //TODO() for getting favourite advertisement
        }
    }

    private fun fetchAds() {
        disposable?.dispose()
        disposable = getAdvertisementsFromNetworkUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ items ->
                if (items.isNotEmpty()) {
                    viewState.postValue(ViewState.ResultData(items))
                } else {
                    viewState.postValue(ViewState.Empty)
                }
            }, {
                if (it.cause is IOException) {
                    viewState.postValue(ViewState.Error(ErrorType.NETWORK_ERROR))
                } else {
                    viewState.postValue(ViewState.Error(ErrorType.SERVER_ERROR))
                }
            })
    }


    override fun onCleared() {
        addRemoveDisposable?.dispose()
        disposable?.dispose()
    }
}
