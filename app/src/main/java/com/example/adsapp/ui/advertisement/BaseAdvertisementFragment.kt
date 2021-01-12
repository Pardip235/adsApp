package com.example.adsapp.ui.advertisement

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adsapp.R
import com.example.adsapp.ui.advertisement.adapter.AdsAdapter
import com.example.adsapp.utils.AdapterCallback
import com.example.adsapp.utils.Constant
import com.example.adsapp.utils.BottomSpacingItemDecoration
import javax.inject.Inject

abstract class BaseAdvertisementFragment :  Fragment(R.layout.fragment_adlist) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var adsAdapter: AdsAdapter

    private var _layoutHolder: AdvertisementLayoutHolder? = null

    protected val layoutHolder: AdvertisementLayoutHolder
        get() = _layoutHolder!!

    lateinit var viewModel: AdvertisementContract.ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _layoutHolder = AdvertisementLayoutHolder(view)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(AdvertisementViewModel::class.java)
        initRecyclerView()
        observeData()
        viewModel.getAdvertisements(filterFavorites = shouldFetchFavoritesOnly).also {
            with(layoutHolder) {
                progressBar.isVisible = true
                loadingTextView.text = requireContext().getString(R.string.loading)
            }
        }
    }

    private fun initRecyclerView() {
        layoutHolder.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(BottomSpacingItemDecoration(Constant.AD_SPACE_SEPARATION))

            adsAdapter = AdsAdapter(object : AdapterCallback {
                override fun addRemoveFromFavorites(id: Int) {
                    TODO("Not yet implemented")
                }

            })

            adapter = adsAdapter
        }
    }

    private fun observeData() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is AdvertisementContract.ViewState.ResultData -> it.handleResultData()
                is AdvertisementContract.ViewState.Error -> it.handleError()
                is AdvertisementContract.ViewState.Empty -> handleEmpty()
            }
        })
    }

    private fun AdvertisementContract.ViewState.ResultData.handleResultData() {
        with(layoutHolder) {
            progressBar.isVisible = false
            recyclerView.isVisible = true
            loadingTextView.isVisible = false
        }
        adsAdapter.submitList(items)
    }

    private fun handleEmpty() {
        with(layoutHolder) {
            progressBar.isVisible = false
            recyclerView.isVisible = false
            loadingTextView.isVisible = true
            loadingTextView.text = requireContext().getText(R.string.empty)
        }
    }

    abstract fun AdvertisementContract.ViewState.Error.handleError()

    abstract val shouldFetchFavoritesOnly: Boolean

    override fun onDestroyView() {
        _layoutHolder = null
        super.onDestroyView()
    }
}
