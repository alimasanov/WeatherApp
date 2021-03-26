package com.example.weatherapp.data

import com.example.weatherapp.data.networkdata.WeatherResponse

data class DataManager(
    var currentWeatherList: MutableList<WeatherResponse> = mutableListOf()
)