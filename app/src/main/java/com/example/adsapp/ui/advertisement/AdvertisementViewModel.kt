package com.example.adsapp.ui.advertisement

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adsapp.utils.ErrorType
import com.example.adsapp.business.usecases.AddAdvertisementToFavoriteUseCase
import com.example.adsapp.business.usecases.GetAdvertisementsFromNetworkUseCase
import com.example.adsapp.business.usecases.GetFavoriteAdvertisementsOnlyUseCase
import com.example.adsapp.business.usecases.RemoveAdvertisementFromFavoritesUseCase
import com.example.adsapp.data.model.Advertisement
import com.example.adsapp.ui.advertisement.AdvertisementContract.ViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import javax.inject.Inject

class AdvertisementViewModel @Inject constructor(
    private val getAdvertisementsFromNetworkUseCase: GetAdvertisementsFromNetworkUseCase,
    private val addAdvertisementToFavoriteUseCase: AddAdvertisementToFavoriteUseCase,
    private val getFavoriteAdvertisementsOnlyUseCase: GetFavoriteAdvertisementsOnlyUseCase,
    private val removeAdvertisementFromFavoritesUseCase: RemoveAdvertisementFromFavoritesUseCase
) : ViewModel(), AdvertisementContract.ViewModel {

    private var addRemoveDisposable: Disposable? = null
    private var disposable: Disposable? = null

    override val viewState: MutableLiveData<ViewState> = MutableLiveData()

    override fun addRemoveFromFavorites(id: Int) {
        val oldViewState = (viewState.value as? ViewState.ResultData) ?: return
        val item = oldViewState.items.firstOrNull { it.id == id } ?: return
        val index = oldViewState.items.indexOfFirst { it.id == id }
        if (item.likeStatus) {
            removeFromFavorites(item, oldViewState, index)
        } else {
            addToFavorites(item, oldViewState, index)
        }
    }

    private fun removeFromFavorites(
        item: Advertisement,
        oldViewState: ViewState.ResultData,
        index: Int
    ) {
        val newAdvertisement = item.copy(likeStatus = false)
        addRemoveDisposable?.dispose()
        addRemoveDisposable = removeAdvertisementFromFavoritesUseCase(newAdvertisement.id)
            .subscribeOn(Schedulers.io())
            .subscribe({
                val newList = oldViewState.items.toMutableList()
                newList[index] = newAdvertisement
                viewState.postValue(oldViewState.copy(items = newList))
            }, {
                //No error handling because we are not removing favorites from server
                it.printStackTrace()
            })
    }

    private fun addToFavorites(
        item: Advertisement,
        oldViewState: ViewState.ResultData,
        index: Int
    ) {
        val newAdvertisement = item.copy(likeStatus = true)
        addRemoveDisposable?.dispose()
        addRemoveDisposable = addAdvertisementToFavoriteUseCase(newAdvertisement)
            .subscribeOn(Schedulers.io())
            .subscribe({
                val newList = oldViewState.items.toMutableList()
                newList[index] = newAdvertisement
                viewState.postValue(oldViewState.copy(items = newList))
            }, {
                //No error handling because we are not adding favorites to server
                it.printStackTrace()
            })
    }

    override fun getAdvertisements(filterFavorites: Boolean) {
        if (filterFavorites) {
            fetchFavouriteAds()
        } else {
            fetchAds()
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

    private fun fetchFavouriteAds() {
        disposable?.dispose()
        disposable = getFavoriteAdvertisementsOnlyUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ items ->
                if (items.isNotEmpty()) {
                    viewState.postValue(ViewState.ResultData(items))
                } else {
                    viewState.postValue(ViewState.Empty)
                }
            }, {
                //can be ignored because its local error from database
                it.printStackTrace()
            })
    }

    override fun onCleared() {
        addRemoveDisposable?.dispose()
        disposable?.dispose()
    }
}
