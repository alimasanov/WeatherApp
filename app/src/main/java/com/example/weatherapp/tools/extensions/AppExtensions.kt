package com.example.weatherapp.tools

import android.content.Context
import android.content.res.Configuration
import androidx.annotation.StringRes
import com.example.weatherapp.App

private fun Context.localise(@StringRes id: Int): String {
    val conf = Configuration(resources.configuration)
    return createConfigurationContext(conf)?.resources?.getString(id) ?: resources.getString(id)
}

fun localise(@StringRes id: Int): String = App.shared.localise(id)