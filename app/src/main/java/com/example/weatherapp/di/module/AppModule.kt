package com.example.weatherapp.di.module

import android.content.Context
import com.example.weatherapp.data.DataManager
import dagger.Module
import dagger.Provides
import io.nlopez.smartlocation.SmartLocation
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideDataManager() = DataManager()

    @Singleton
    @Provides
    fun provideSmartLocation(context: Context): SmartLocation = SmartLocation.with(context)
}