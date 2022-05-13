package com.techyourchance.dagger2course.common.composition

import android.app.Application
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.screens.common.screens.ScreensNavigator
import dagger.Component

@Component(modules = [ActivityModule::class], dependencies = [AppComponent::class])
@ActivityScope
interface ActivityComponent {

    fun activity(): AppCompatActivity

    fun application(): Application

    fun fragmentManager(): FragmentManager

    fun layoutInflater(): LayoutInflater

    fun stackoverflowApi(): StackoverflowApi

    fun screensNavigator(): ScreensNavigator
}