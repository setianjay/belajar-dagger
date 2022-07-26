package com.techyourchance.dagger2course.common.di.activity

import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.common.di.presentation.PresentationComponent
import com.techyourchance.dagger2course.common.di.presentation.PresentationModule
import com.techyourchance.dagger2course.common.di.presentation.UseCaseModule
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