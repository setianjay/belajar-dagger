package com.techyourchance.dagger2course.common.composition

import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.techyourchance.dagger2course.data.usecase.FetchQuestionsUseCase
import com.techyourchance.dagger2course.data.usecase.FetchQuestionsUseCaseImpl
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.screens.common.dialogs.DialogsNavigator
import com.techyourchance.dagger2course.screens.common.screens.ScreensNavigator
import com.techyourchance.dagger2course.screens.common.viewmvc.ViewMvcFactory

/**
 * this class provide service presentation scope
 * */
class PresentationCompositionRoot(private val activityCompositionRoot: ActivityCompositionRoot) {

    private val fragmentManager: FragmentManager get() = activityCompositionRoot.fragmentManager
    private val layoutInflater: LayoutInflater get() = activityCompositionRoot.layoutInflater
    private val stackoverflowApi: StackoverflowApi get() = activityCompositionRoot.stackoverflowApi

    val dialogsNavigator: DialogsNavigator get() = DialogsNavigator(fragmentManager)

    val fetchQuestionsUseCase: FetchQuestionsUseCase
        get() = FetchQuestionsUseCaseImpl(
            stackoverflowApi
        )

    val viewMvcFactory: ViewMvcFactory get() = ViewMvcFactory(layoutInflater)

    val screensNavigator: ScreensNavigator get() = activityCompositionRoot.screensNavigator
}