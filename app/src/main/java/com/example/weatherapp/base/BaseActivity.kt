package com.example.weatherapp.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.R
import com.example.weatherapp.di.component.DaggerAppComponent
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import java.security.Permission
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {
    abstract val layoutId: Int

    private val appComponent by lazy { DaggerAppComponent.factory().create(this) }

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator by lazy { AppNavigator(this, R.id.container) }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState, persistentState)
        setContentView(layoutId)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}