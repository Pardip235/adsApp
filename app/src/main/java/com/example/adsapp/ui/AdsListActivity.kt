package com.example.adsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.adsapp.R

import kotlinx.android.synthetic.main.activity_adlist.*


class AdsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adlist)
        setSupportActionBar(toolBar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(bottom_navigation_view, navHostFragment.navController)
    }

    override fun onSupportNavigateUp() =
        NavHostFragment.findNavController(host_fragment).navigateUp()
}
