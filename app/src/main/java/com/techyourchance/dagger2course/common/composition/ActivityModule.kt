package com.techyourchance.dagger2course.common.composition

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.techyourchance.dagger2course.screens.common.screens.ScreensNavigator
import dagger.Module
import dagger.Provides

/**
 * this class provide service activity scope
 * */
@Module
class ActivityModule(
    private val activity: AppCompatActivity
) {
    @Provides
    fun activity(): AppCompatActivity = activity

    @Provides
    fun fragmentManager(activity: AppCompatActivity): FragmentManager = activity.supportFragmentManager

    @Provides
    fun layoutInflater(activity: AppCompatActivity): LayoutInflater = LayoutInflater.from(activity)

    @Provides
    @ActivityScope
    fun screensNavigator(activity: AppCompatActivity): ScreensNavigator = ScreensNavigator(activity)

}