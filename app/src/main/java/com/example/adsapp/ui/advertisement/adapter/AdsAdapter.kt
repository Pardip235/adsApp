package com.example.adsapp.ui.advertisement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.adsapp.R
import com.example.adsapp.data.model.Advertisement
import com.example.adsapp.ui.advertisement.adapter.AdsDiffCallback.DATA_COMPARATOR
import com.example.adsapp.utils.AdapterCallback
import com.example.adsapp.utils.Constant
import com.example.adsapp.utils.ImageUtils

class AdsAdapter internal constructor(
    private val callback: AdapterCallback
) : ListAdapter<Advertisement, RecyclerView.ViewHolder>(DATA_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AdsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.advertisement_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val advertisement = getItem(position)
        (holder as AdsViewHolder).apply {
            onBind(advertisement)
            setClickListener(advertisement)
        }
    }

    private fun AdsViewHolder.setClickListener(advertisement: Advertisement) {
        addToFavButton.setOnClickListener {
            callback.addRemoveFromFavorites(advertisement.id)
        }
    }

    private fun AdsViewHolder.onBind(advertisement: Advertisement) {
        adPrice.text = advertisement.price?.value?.let { "$it Kr" }
        location.text = advertisement.location ?: ""
        advertisementTitle.text = advertisement.description ?: ""

        val imageUrl = Constant.BASE_IMAGE_URL + advertisement.image.url
        ImageUtils.loadImage(imageUrl, adImage)
        adImage.adjustViewBounds = true

        val drawable = ContextCompat.getDrawable(
            itemView.context,
            if (advertisement.likeStatus) R.drawable.ic_favourite_selected
            else R.drawable.ic_favourite_unselected
        )
        addToFavButton.setImageDrawable(drawable)
        addToFavButton.isSelected = advertisement.likeStatus
    }

    class AdsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val adImage = itemView.findViewById<ImageView>(R.id.ads_image_view)
        val adPrice = itemView.findViewById<TextView>(R.id.ads_price)
        val location = itemView.findViewById<TextView>(R.id.ads_location)
        val advertisementTitle = itemView.findViewById<TextView>(R.id.ad_title)
        val addToFavButton = itemView.findViewById<ImageView>(R.id.add_to_fav_icon)
    }
}
