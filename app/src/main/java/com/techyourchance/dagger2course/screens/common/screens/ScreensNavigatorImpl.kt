package com.techyourchance.dagger2course.screens.common.screens

import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.screens.questiondetails.QuestionDetailsActivity

class ScreensNavigatorImpl(private val activity: AppCompatActivity): ScreensNavigator {

    override fun navigateBack(){
        activity.onBackPressed()
    }

    override fun toDetailsQuestion(questionId: String){
        QuestionDetailsActivity.start(activity, questionId)
    }
}