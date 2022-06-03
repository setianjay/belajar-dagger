package com.techyourchance.dagger2course.screens.common.screens

import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.screens.questiondetails.QuestionDetailsActivity
import com.techyourchance.dagger2course.screens.viewmodel.ViewModelActivity

class ScreensNavigatorImpl(private val activity: AppCompatActivity): ScreensNavigator {

    override fun navigateBack(){
        activity.onBackPressed()
    }

    override fun toDetailsQuestion(questionId: String){
        QuestionDetailsActivity.start(activity, questionId)
    }

    override fun toViewModel() {
        ViewModelActivity.start(activity)
    }
}