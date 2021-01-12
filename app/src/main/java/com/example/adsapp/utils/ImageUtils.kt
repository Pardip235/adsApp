package com.example.adsapp.utils

import android.widget.ImageView
import com.example.adsapp.R
import com.squareup.picasso.Picasso

object ImageUtils {
    fun loadImage(url: String, imageView: ImageView) {
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.no_image)
            .into(imageView)
    }
}