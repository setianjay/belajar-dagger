package com.techyourchance.dagger2course.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.MyApplication
import com.techyourchance.dagger2course.common.di.activity.ActivityComponent
import com.techyourchance.dagger2course.common.di.activity.ActivityModule
import com.techyourchance.dagger2course.common.di.app.AppComponent
import com.techyourchance.dagger2course.common.di.presentation.PresentationComponent
import com.techyourchance.dagger2course.common.di.presentation.PresentationModule
import com.techyourchance.dagger2course.common.di.presentation.UseCaseModule

open class BaseActivity: AppCompatActivity() {

    private val appComponent: AppComponent get() = (application as MyApplication).appComponent

    private val activityComponent: ActivityComponent by lazy {
        appComponent.newActivityComponentBuilder()
            .activity(this)
            .activityModule(ActivityModule)
            .build()
    }

    private val presentationComponent: PresentationComponent by lazy {
       activityComponent.newPresentationComponent(PresentationModule(), UseCaseModule())
    }

    protected val injector: PresentationComponent get() = presentationComponent

}