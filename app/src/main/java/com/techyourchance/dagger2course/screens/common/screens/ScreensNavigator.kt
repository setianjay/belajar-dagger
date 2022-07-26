package com.techyourchance.dagger2course.screens.common.screens

interface ScreensNavigator {

    fun navigateBack()

    fun toDetailsQuestion(questionId: String)

    fun toViewModel()
}