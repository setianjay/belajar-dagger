package com.techyourchance.dagger2course.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.MyApplication
import com.techyourchance.dagger2course.common.composition.ActivityCompositionRoot
import com.techyourchance.dagger2course.common.composition.AppCompositionRoot
import com.techyourchance.dagger2course.common.composition.PresentationCompositionRoot
import com.techyourchance.dagger2course.common.composition.dependencyinjection.Injector

open class BaseActivity: AppCompatActivity() {

    private val appCompositionRoot: AppCompositionRoot get() = (application as MyApplication).appCompositionRoot
    private val activityCompositionRoot: ActivityCompositionRoot by lazy {
        ActivityCompositionRoot(this, appCompositionRoot)
    }
    private val compositionRoot: PresentationCompositionRoot by lazy {
        PresentationCompositionRoot(activityCompositionRoot)
    }
    protected val injector: Injector get() = Injector(compositionRoot)

}