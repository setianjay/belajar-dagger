package com.techyourchance.dagger2course.common.composition

import dagger.Subcomponent

@Subcomponent(modules = [ActivityModule::class])
@ActivityScope
interface ActivityComponent {

    fun newPresentationComponent(
        presentationModule: PresentationModule,
        useCaseModule: UseCaseModule
    ): PresentationComponent
}