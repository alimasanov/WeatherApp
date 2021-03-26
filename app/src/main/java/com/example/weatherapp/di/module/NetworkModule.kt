package com.example.weatherapp.di.module

import com.example.weatherapp.network.Api
import com.example.weatherapp.network.tools.ServiceGenerator
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = ServiceGenerator.getRetrofit(okHttpClient)

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = ServiceGenerator.getOkHttpClient()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

}