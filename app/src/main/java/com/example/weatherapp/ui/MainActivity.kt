package com.example.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.example.weatherapp.R
import com.example.weatherapp.base.BaseActivity
import java.security.Permission
import javax.inject.Inject

class MainActivity : BaseActivity() {

    override val layoutId = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
//        router.newRootScreen()
    }
}