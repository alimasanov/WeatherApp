package com.example.weatherapp.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.example.weatherapp.ui.current.CurrentWeatherFragment
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun appSystemSettingsScreen(context: Context) = ActivityScreen {
        Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.fromParts("package", context.packageName, null)
        }
    }

    fun currentWeatherScreen() = FragmentScreen { CurrentWeatherFragment() }

}