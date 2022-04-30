package com.techyourchance.dagger2course

import android.app.Application
import android.util.Log
import com.techyourchance.dagger2course.common.composition.AppCompositionRoot

class MyApplication: Application() {

    lateinit var appCompositionRoot: AppCompositionRoot

    override fun onCreate() {
        appCompositionRoot = AppCompositionRoot()
        Log.d(this::class.simpleName, "appCompositionRoot in application: $appCompositionRoot")
        super.onCreate()
    }

}