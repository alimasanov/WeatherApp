package com.example.weatherapp.di.component

import android.content.Context
import com.example.weatherapp.App
import com.example.weatherapp.base.BaseActivity
import com.example.weatherapp.di.module.AppModule
import com.example.weatherapp.di.module.NavigationModule
import com.example.weatherapp.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, NavigationModule::class, AppModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(app: App)
    fun inject(activity: BaseActivity)
}