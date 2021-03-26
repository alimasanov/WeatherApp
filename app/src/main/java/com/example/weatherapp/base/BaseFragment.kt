package com.example.weatherapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.example.weatherapp.di.component.DaggerFragmentComponent
import com.example.weatherapp.di.component.FragmentComponent

abstract class BaseFragment : MvpAppCompatFragment() {
    abstract val layoutId: Int

    private val fragmentComponent by lazy { DaggerFragmentComponent.factory().create(context!!, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        fragmentComponent.let(this::inject)
        super.onCreate(savedInstanceState)
    }

    abstract fun inject(component: FragmentComponent)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }
}