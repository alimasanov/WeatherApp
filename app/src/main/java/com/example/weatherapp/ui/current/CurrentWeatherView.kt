package com.example.weatherapp.ui.current

import com.example.weatherapp.base.BaseView
import com.example.weatherapp.data.networkdata.WeatherResponse

interface CurrentWeatherView : BaseView {
    fun initView(weather: WeatherResponse)
}