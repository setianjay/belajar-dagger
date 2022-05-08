package com.techyourchance.dagger2course.common.composition

import android.app.Application
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.screens.common.screens.ScreensNavigator
import dagger.Module
import dagger.Provides

/**
 * this class provide service activity scope
 * */
@Module
class ActivityModule(
    private val activity: AppCompatActivity,
    private val appComponent: AppComponent
) {
    @Provides
    fun activity(): AppCompatActivity = activity

    @Provides
    fun application(): Application = appComponent.application()

    @Provides
    fun fragmentManager(activity: AppCompatActivity): FragmentManager = activity.supportFragmentManager

    @Provides
    fun layoutInflater(activity: AppCompatActivity): LayoutInflater = LayoutInflater.from(activity)

    @Provides
    fun stackoverflowApi(): StackoverflowApi = appComponent.stackoverflowApi()

    @Provides
    fun screensNavigator(activity: AppCompatActivity): ScreensNavigator = ScreensNavigator(activity)

}