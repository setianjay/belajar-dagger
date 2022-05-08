package com.techyourchance.dagger2course.common.composition

import com.techyourchance.dagger2course.data.usecase.FetchQuestionsUseCase
import com.techyourchance.dagger2course.screens.common.dialogs.DialogsNavigator
import com.techyourchance.dagger2course.screens.common.screens.ScreensNavigator
import com.techyourchance.dagger2course.screens.common.viewmvc.ViewMvcFactory
import dagger.Component

@Component(modules = [PresentationModule::class])
interface PresentationComponent {

    fun dialogsNavigator(): DialogsNavigator

    fun fetchQuestionsUseCase(): FetchQuestionsUseCase

    fun viewMvcFactory(): ViewMvcFactory

    fun screensNavigator(): ScreensNavigator
}