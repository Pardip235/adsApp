package com.example.adsapp.ui.advertisement

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.adsapp.R

class AdvertisementLayoutHolder(rootView: View) {
    val loadingTextView: TextView = rootView.findViewById(R.id.loadingTextView)
    val progressBar: ProgressBar = rootView.findViewById(R.id.progressBar)
    val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerView)
}
