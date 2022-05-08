package com.techyourchance.dagger2course

import android.app.Application
import android.util.Log
import com.techyourchance.dagger2course.common.composition.AppComponent
import com.techyourchance.dagger2course.common.composition.AppModule
import com.techyourchance.dagger2course.common.composition.DaggerAppComponent

class MyApplication: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
    }

}