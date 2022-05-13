package com.techyourchance.dagger2course.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.MyApplication
import com.techyourchance.dagger2course.common.composition.*

open class BaseActivity: AppCompatActivity() {

    private val appComponent: AppComponent get() = (application as MyApplication).appComponent

    private val activityComponent: ActivityComponent by lazy {
        appComponent.newActivityComponent(ActivityModule(this))
    }

    private val presentationComponent: PresentationComponent by lazy {
       activityComponent.newPresentationComponent(PresentationModule())
    }

    protected val injector: PresentationComponent get() = presentationComponent

}