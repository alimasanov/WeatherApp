package com.example.weatherapp.ui.current

import com.example.weatherapp.base.BasePresenter
import com.example.weatherapp.data.networkdata.WeatherResponse
import com.example.weatherapp.network.repository.CurrentWeatherRepository
import com.example.weatherapp.tools.LocationManager
import com.example.weatherapp.tools.WeatherPermission
import com.example.weatherapp.tools.extensions.addTo
import com.example.weatherapp.tools.extensions.viewStateProgress
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class CurrentWeatherPresenter @Inject constructor(
    private val repository: CurrentWeatherRepository,
    private val permission: WeatherPermission,
    private val locationManager: LocationManager
) : BasePresenter<CurrentWeatherView>() {

    private lateinit var coordinates: LatLng

    override fun attachView(view: CurrentWeatherView?) {
        super.attachView(view)
        getLocationPermission()
    }

    private fun getLocationPermission() {
        permission.ensure(WeatherPermission.Permission.LOCATION)
            .andThen(locationManager.myCoordinate())
            .subscribe({
                coordinates = it
                getCurrentWeather(it.latitude, it.longitude)
            }, {  })
            .addTo(disposables)
    }

    private fun checkSavedData() {}

    private fun getCurrentWeather(lat: Double, lng: Double) {
        repository
            .getCurrentWeather(lat, lng)
            .viewStateProgress(viewState)
            .doOnSuccess { initView(it) }
            .subscribe()
            .addTo(disposables)
    }

    private fun initView(weatherList: MutableList<WeatherResponse>) {
        weatherList.map {

        }
    }

    private fun coordinatesDiff() {

    }

    private fun getCurrentCity() {}
}