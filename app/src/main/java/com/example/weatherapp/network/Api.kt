package com.example.weatherapp.network

import retrofit2.http.*
import com.example.weatherapp.network.tools.RequestFields.CITY_NAME
import com.example.weatherapp.network.tools.RequestFields.LATITUDE
import com.example.weatherapp.network.tools.RequestFields.LONGITUDE
import com.example.weatherapp.network.tools.RequestFields.NUMBER_OF_RETURNED_DAYS
import com.example.weatherapp.network.tools.Urls.CLIMATIC_FORECAST_30_DAYS
import com.example.weatherapp.network.tools.Urls.CURRENT_WEATHER_DATA
import com.example.weatherapp.network.tools.Urls.DAILY_FORECAST_16_DAYS
import com.example.weatherapp.network.tools.Urls.HOURLY_FORECAST_4_DAYS
import com.google.gson.JsonObject
import io.reactivex.Single

interface Api {

    // current weather

    @GET(CURRENT_WEATHER_DATA)
    fun getCurrentByCityName(
        @Query(CITY_NAME) cityName: String
    ): Single<JsonObject>

    @GET(CURRENT_WEATHER_DATA)
    fun getCurrentByCoordinates(
        @Query(LATITUDE) latitude: Double,
        @Query(LONGITUDE) longitude: Double
    ): Single<JsonObject>

    // hourly for 4 days

    @GET(HOURLY_FORECAST_4_DAYS)
    fun getFourDaysWeatherByCityName(
        @Query(CITY_NAME) cityName: String
    ): Single<JsonObject>

    @GET(HOURLY_FORECAST_4_DAYS)
    fun getFourDaysWeatherByCoordinates(
        @Query(LATITUDE) latitude: Float,
        @Query(LONGITUDE) longitude: Float
    ): Single<JsonObject>

    // daily for 16 days

    @GET(DAILY_FORECAST_16_DAYS)
    fun getSixteenDaysWeatherByCityName(
        @Query(CITY_NAME) cityName: String,
        @Query(NUMBER_OF_RETURNED_DAYS) numberOfReturnedDays: Int = 16 // from 1 to 16
    ): Single<JsonObject>

    @GET(DAILY_FORECAST_16_DAYS)
    fun getSixteenDaysWeatherByCoordinates(
        @Query(LATITUDE) latitude: Float,
        @Query(LONGITUDE) longitude: Float,
        @Query(NUMBER_OF_RETURNED_DAYS) numberOfReturnedDays: Int = 16 // from 1 to 16
    ): Single<JsonObject>

    // daily for 30 days

    @GET(CLIMATIC_FORECAST_30_DAYS)
    fun getThirtyDaysWeatherByCityName(
        @Query(CITY_NAME) cityName: String,
        @Query(NUMBER_OF_RETURNED_DAYS) numberOfReturnedDays: Int = 16 // from 1 to 30
    ): Single<JsonObject>

    @GET(CLIMATIC_FORECAST_30_DAYS)
    fun getThirtyDaysWeatherByCoordinates(
        @Query(LATITUDE) latitude: Float,
        @Query(LONGITUDE) longitude: Float,
        @Query(NUMBER_OF_RETURNED_DAYS) numberOfReturnedDays: Int = 16 // from 1 to 30
    ): Single<JsonObject>
}