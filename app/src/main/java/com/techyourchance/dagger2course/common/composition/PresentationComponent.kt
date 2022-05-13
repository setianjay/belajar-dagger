package com.techyourchance.dagger2course.common.composition

import com.techyourchance.dagger2course.screens.questiondetails.QuestionDetailsActivity
import com.techyourchance.dagger2course.screens.questionslist.QuestionsListActivity
import dagger.Component

@Component(modules = [PresentationModule::class], dependencies = [ActivityComponent::class])
@PresentationScope
interface PresentationComponent {

    fun inject(activity: QuestionsListActivity)
    fun inject(activity: QuestionDetailsActivity)

}