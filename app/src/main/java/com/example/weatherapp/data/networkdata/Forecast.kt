package com.example.weatherapp.data.networkdata

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Forecast(
    val forecastTime: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: Temperature,
    val feelsLike: Temperature,
    val atmPressure: Int,
    val humidity: Int,
    val weather: Weather,
    val speed: Double,
    val windDirectionDegrees: Int?,
    val clouds: Int?,
    val rain: Int?,
    val snow: Int?
) : Parcelable

@Parcelize
data class Temperature(
    var min: Double? = 0.0,
    var max: Double? = 0.0,
    val morning: Double,
    val day: Double,
    val evening: Double,
    val night: Double
) : Parcelable

@Parcelize
data class Weather(
    val id: Long,
    val weather: String,
    val description: String,
    val iconCode: String
) : Parcelable