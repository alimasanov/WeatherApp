package com.example.weatherapp.di.module

import androidx.fragment.app.FragmentActivity
import com.example.weatherapp.di.NamedValues
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class NavigationModule {

    @Provides
    @Singleton
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    @Singleton
    fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Provides
    @Singleton
    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder = cicerone.getNavigatorHolder()

    @Provides
    @Singleton
    @Named(NamedValues.MAIN_TAB_ROUTER)
    fun provideMainTabCicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    @Singleton
    @Named(NamedValues.MAIN_TAB_ROUTER)
    fun provideMainTabRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Provides
    @Singleton
    @Named(NamedValues.MAIN_TAB_ROUTER)
    fun provideMainTabNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder = cicerone.getNavigatorHolder()

}