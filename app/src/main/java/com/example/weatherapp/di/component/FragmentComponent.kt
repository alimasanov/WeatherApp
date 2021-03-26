package com.example.weatherapp.di.component

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.weatherapp.di.NamedValues
import com.example.weatherapp.di.annotation.FragmentContext
import com.example.weatherapp.network.repository.CurrentWeatherRepository
import com.example.weatherapp.ui.current.CurrentWeatherFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component
interface FragmentComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance @FragmentContext context: Context, @BindsInstance fragment: Fragment): FragmentComponent
    }

    fun inject(fragment: CurrentWeatherFragment)
}