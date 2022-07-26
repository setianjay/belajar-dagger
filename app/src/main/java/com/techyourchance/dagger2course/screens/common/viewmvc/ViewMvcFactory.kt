package com.techyourchance.dagger2course.screens.common.viewmvc

import android.view.LayoutInflater
import android.view.ViewGroup
import com.techyourchance.dagger2course.screens.questiondetails.QuestionDetailsViewMvc
import com.techyourchance.dagger2course.screens.questionslist.QuestionsListViewMvc
import com.techyourchance.dagger2course.screens.viewmodel.ViewModelViewMvc

class ViewMvcFactory(private val layoutInflater: LayoutInflater) {

    fun newQuestionsListViewMvc(parent: ViewGroup?): QuestionsListViewMvc{
        return QuestionsListViewMvc(layoutInflater, parent)
    }

    fun newQuestionDetailsViewMvc(parent: ViewGroup?): QuestionDetailsViewMvc{
        return QuestionDetailsViewMvc(layoutInflater, parent)
    }

    fun newViewModelViewMvc(parent: ViewGroup?): ViewModelViewMvc{
        return ViewModelViewMvc(layoutInflater, parent)
    }
}