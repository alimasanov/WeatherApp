package com.example.weatherapp.base

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.weatherapp.data.DataManager
import com.example.weatherapp.di.NamedValues
import com.example.weatherapp.di.annotation.FragmentContext
import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import javax.inject.Named

@FragmentContext
@InjectViewState
abstract class BasePresenter<V : BaseView> : MvpPresenter<V>() {

    @Inject
    lateinit var router: Router
    @Inject
    @Named(NamedValues.FRAGMENT_CONTEXT)
    lateinit var context: Context
    @Inject
    lateinit var dataManager: DataManager

    open val disposables = CompositeDisposable()
    private val disposablesByKey = mutableMapOf<String, Disposable>()

    override fun onDestroy() {
        disposables.clear()
        disposables.inFragment { dispose() }
        super.onDestroy()
    }

    private fun CompositeDisposable.inFragment(editor: CompositeDisposable.() -> Unit) {
        editor()
        disposablesByKey.clear()
    }


}