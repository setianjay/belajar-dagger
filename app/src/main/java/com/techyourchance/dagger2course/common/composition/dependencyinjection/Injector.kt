package com.techyourchance.dagger2course.common.composition.dependencyinjection

import com.techyourchance.dagger2course.common.composition.PresentationCompositionRoot
import com.techyourchance.dagger2course.screens.questiondetails.QuestionDetailsActivity
import com.techyourchance.dagger2course.screens.questionslist.QuestionsListActivity

/**
 * this class injecting service for client needed
 * */
class Injector(private val compositionRoot: PresentationCompositionRoot) {

    fun inject(activity: QuestionsListActivity){
        activity.apply {
            this.dialogsNavigator = compositionRoot.dialogsNavigator
            this.fetchQuestionsUseCase = compositionRoot.fetchQuestionsUseCase
            this.screensNavigator = compositionRoot.screensNavigator
            this.viewMvcFactory = compositionRoot.viewMvcFactory
        }
    }

    fun inject(activity: QuestionDetailsActivity){
        activity.apply {
            this.dialogsNavigator = compositionRoot.dialogsNavigator
            this.fetchQuestionsUseCase = compositionRoot.fetchQuestionsUseCase
            this.screensNavigator = compositionRoot.screensNavigator
            this.viewMvcFactory = compositionRoot.viewMvcFactory
        }
    }
}