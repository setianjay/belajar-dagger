package com.techyourchance.dagger2course.common.composition

import dagger.Component

@Component(modules = [AppModule::class])
@AppScope
interface AppComponent {

    fun newActivityComponentBuilder(): ActivityComponent.Builder
}