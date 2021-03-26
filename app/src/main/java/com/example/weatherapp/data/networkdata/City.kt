package com.example.weatherapp.data.networkdata

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(
    val id: Long,
    val name: String,
    val coordinates: Coordinates,
    val countryCode: String
) : Parcelable

@Parcelize
data class Coordinates(
    val lat: Double,
    val lon: Double
) : Parcelable
