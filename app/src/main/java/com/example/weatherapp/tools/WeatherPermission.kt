package com.example.weatherapp.tools

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.weatherapp.di.annotation.FragmentContext
import com.example.weatherapp.di.annotation.FragmentScope
import com.example.weatherapp.navigation.Screens
import com.github.terrakok.cicerone.Router
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@FragmentScope
class WeatherPermission @Inject constructor(
    private val fragment: Fragment,
    @FragmentContext
    private val context: Context,
    private val router: Router
) : LifecycleObserver {

    enum class Permission(val permissions: List<String>) {
        LOCATION(
            listOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private val Permission.isGranted: Boolean
        get() = !permissions.map(RxPermissions(fragment)::isGranted).contains(false)

    sealed class Error(private val text: String) : Exception(text) {
        class NoAccess : Error("no access")
    }

    private var alertDialog: AlertDialog? = null

    private fun Permission.alertDialogBuildAndShow(title: String) = AlertDialog.Builder(context)
        .setTitle(null)
        .setMessage(title)
        .setPositiveButton("Открыть настройки") { _, _ ->
            router.navigateTo(Screens.appSystemSettings(context))
        }
        .setCancelable(true)
        .create().apply {
            alertDialog = this
            show()
        }

    init {
        fragment.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        alertDialog?.hide()
    }

    fun ensure(permission: Permission): Completable =
        if (permission.isGranted) Completable.complete() else
            RxPermissions(fragment)
                .request(*permission.permissions.toTypedArray())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    if (!it) {
                        when (permission) {
                            Permission.LOCATION -> {
                                permission.alertDialogBuildAndShow("Необходимы разрешения для получения геопозиции")
                            }
                        }
                        throw Error.NoAccess()
                    }
                }
                .ignoreElements()

}