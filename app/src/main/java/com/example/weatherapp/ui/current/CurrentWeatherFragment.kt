package com.example.weatherapp.ui.current

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.weatherapp.base.BaseFragment
import com.example.weatherapp.di.component.FragmentComponent
import javax.inject.Inject

class CurrentWeatherFragment : BaseFragment() {
    override val layoutId: Int
        get() = TODO("Not yet implemented")

    @Inject
    @InjectPresenter
    lateinit var presenter: CurrentWeatherPresenter

    override fun inject(component: FragmentComponent) = component.inject(this)

    @ProvidePresenter
    fun providePresenter() = presenter
}