package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.di.component.DaggerAppComponent

class App : Application() {

    val appComponent by lazy { DaggerAppComponent.factory().create(this) }

    override fun onCreate() {
        super.onCreate()
        shared = this
        appComponent.inject(this)
    }

    companion object {
        lateinit var shared: App private set
    }
}