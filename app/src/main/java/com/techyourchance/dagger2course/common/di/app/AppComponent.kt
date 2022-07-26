package com.techyourchance.dagger2course.common.di.app

import com.techyourchance.dagger2course.common.di.activity.ActivityComponent
import dagger.Component

@Component(modules = [AppModule::class])
@AppScope
interface AppComponent {

    fun newActivityComponentBuilder(): ActivityComponent.Builder
}