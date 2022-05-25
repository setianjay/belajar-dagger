package com.techyourchance.dagger2course.common.composition

import androidx.appcompat.app.AppCompatActivity
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [ActivityModule::class])
@ActivityScope
interface ActivityComponent {

    @Subcomponent.Builder
    interface Builder{
        @BindsInstance
        fun activity(activity: AppCompatActivity): Builder
        fun activityModule(activityModule: ActivityModule): Builder
        fun build(): ActivityComponent
    }

    fun newPresentationComponent(
        presentationModule: PresentationModule,
        useCaseModule: UseCaseModule
    ): PresentationComponent
}