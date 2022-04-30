package com.techyourchance.dagger2course.common.composition

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.techyourchance.dagger2course.data.usecase.FetchQuestionsUseCase
import com.techyourchance.dagger2course.data.usecase.FetchQuestionsUseCaseImpl
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.screens.common.dialogs.DialogsNavigator
import com.techyourchance.dagger2course.screens.common.screens.ScreensNavigator
import com.techyourchance.dagger2course.screens.common.viewmvc.ViewMvcFactory

/**
 * this class provide service activity scope
 * */
class ActivityCompositionRoot(
    private val activity: AppCompatActivity,
    private val appCompositionRoot: AppCompositionRoot
) {

    val fragmentManager: FragmentManager get() = activity.supportFragmentManager
    val layoutInflater: LayoutInflater get() = LayoutInflater.from(activity)
    val stackoverflowApi: StackoverflowApi get() = appCompositionRoot.stackoverflowApi
    val screensNavigator: ScreensNavigator by lazy {
        ScreensNavigator(activity)
    }

}