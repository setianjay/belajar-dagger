package com.techyourchance.dagger2course.common.composition

import com.techyourchance.dagger2course.screens.questiondetails.QuestionDetailsActivity
import com.techyourchance.dagger2course.screens.questionslist.QuestionsListActivity
import com.techyourchance.dagger2course.screens.viewmodel.ViewModelActivity
import dagger.Subcomponent

@Subcomponent(modules = [PresentationModule::class, UseCaseModule::class])
@PresentationScope
interface PresentationComponent {

    fun inject(activity: QuestionsListActivity)
    fun inject(activity: QuestionDetailsActivity)
    fun inject(activity: ViewModelActivity)
}