package com.example.adsapp.ui.advertisement.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.adsapp.data.model.Advertisement

object AdsDiffCallback {
    val DATA_COMPARATOR = object : DiffUtil.ItemCallback<Advertisement>() {
        override fun areItemsTheSame(oldItem: Advertisement, newItem: Advertisement): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Advertisement,
            newItem: Advertisement
        ): Boolean =
            oldItem.likeStatus == newItem.likeStatus
    }
}
