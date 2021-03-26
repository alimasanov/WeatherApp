package com.example.weatherapp.base

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface BaseView : MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun startLoading()
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun completeLoading()
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun errorLoading(throwable: Throwable)
}