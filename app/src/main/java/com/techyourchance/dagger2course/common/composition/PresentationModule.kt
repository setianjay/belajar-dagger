package com.techyourchance.dagger2course.common.composition

import android.app.Application
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.techyourchance.dagger2course.data.usecase.FetchQuestionsUseCase
import com.techyourchance.dagger2course.data.usecase.FetchQuestionsUseCaseImpl
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.screens.common.dialogs.DialogsNavigator
import com.techyourchance.dagger2course.screens.common.viewmvc.ViewMvcFactory
import dagger.Module
import dagger.Provides

/**
 * this class provide service presentation scope
 * */
@Module
class PresentationModule(private val activityComponent: ActivityComponent) {

    @Provides
    fun activity(): AppCompatActivity = activityComponent.activity()

    @Provides
    fun application(): Application = activityComponent.application()

    @Provides
    fun fragmentManager() = activityComponent.fragmentManager()

    @Provides
    fun layoutInflater() = activityComponent.layoutInflater()

    @Provides
    fun stackoverflowApi() = activityComponent.stackoverflowApi()

    @Provides
    fun dialogsNavigator(fragmentManager: FragmentManager) = DialogsNavigator(fragmentManager)

    @Provides
    fun fetchQuestionsUseCase(stackoverflowApi: StackoverflowApi): FetchQuestionsUseCase = FetchQuestionsUseCaseImpl(stackoverflowApi)

    @Provides
    fun viewMvcFactory(layoutInflater: LayoutInflater) = ViewMvcFactory(layoutInflater)

    @Provides
    fun screensNavigator() = activityComponent.screensNavigator()
}