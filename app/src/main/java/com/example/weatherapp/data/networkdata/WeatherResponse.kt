package com.example.weatherapp.data.networkdata

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherResponse(
    val city: City,
    val weatherList: MutableList<Forecast>
) : Parcelable
