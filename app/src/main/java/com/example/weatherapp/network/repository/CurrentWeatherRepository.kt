package com.example.weatherapp.network.repository

import com.example.weatherapp.data.DataManager
import com.example.weatherapp.data.networkdata.*
import com.example.weatherapp.network.Api
import com.example.weatherapp.tools.extensions.parse
import com.example.weatherapp.tools.extensions.parseList
import com.example.weatherapp.tools.extensions.parseMaybe
import com.google.gson.JsonObject
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CurrentWeatherRepository @Inject constructor(
    private val api: Api,
    private val dataManager: DataManager
) : BaseRepository() {

    private fun save(weatherResponse: JsonObject) {
        with(weatherResponse.parseWeatherResponse()) {
            dataManager.currentWeatherList.apply {
                removeIf { it.city.id == this@with.city.id }
                add(this@with)
            }
            this
        }
    }

    private fun Single<JsonObject>.subscribeResponse() = this
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map {
            save(it)
            dataManager.currentWeatherList
        }

    fun getCurrentWeather(cityName: String) = api
        .getCurrentByCityName(cityName)
        .subscribeResponse()

    fun getCurrentWeather(latitude: Double, longitude: Double) = api
        .getCurrentByCoordinates(latitude, longitude)
        .subscribeResponse()

    private fun JsonObject.parseWeatherResponse(): WeatherResponse = WeatherResponse(
        city = this.parseCity(),
        weatherList = this.parseWeatherList()
    )

    private fun JsonObject.parseCity(): City = City(
        id = this parse "id",
        name = this parse "name",
        coordinates = this.getAsJsonObject("coord").parseCoordinates(),
        countryCode = this parse "country"
    )

    private fun JsonObject.parseCoordinates(): Coordinates = Coordinates(
        lat = this parse "lat",
        lon = this parse "lon"
    )

    private fun JsonObject.parseWeatherList(): MutableList<Forecast> = this.parseList("list").map {
        Forecast(
            forecastTime = it parse "dt",
            sunrise = it parse "sunrise",
            sunset = it parse "sunset",
            temp = it.getAsJsonObject("temperature").parseTemperature(),
            feelsLike = it.getAsJsonObject("feels_like").parseTemperature(),
            atmPressure = it parse "pressure",
            humidity = it parse "humidity",
            weather = it.getAsJsonObject("weather").parseWeather(),
            speed = it parse "speed",
            windDirectionDegrees = it parseMaybe "deg",
            clouds = it parseMaybe "clouds",
            rain = it parseMaybe "rain",
            snow = it parseMaybe "snow"
        )
    }.toMutableList()

    private fun JsonObject.parseTemperature(): Temperature = Temperature(
        min = this parseMaybe "min",
        max = this parseMaybe "max",
        morning = this parse "morn",
        day = this parse "day",
        evening = this parse "eve",
        night = this parse "night"
    )

    private fun JsonObject.parseWeather(): Weather = Weather(
        id = this parse "id",
        weather = this parse "main",
        description = this parse "description",
        iconCode = this parse "icon"
    )

}