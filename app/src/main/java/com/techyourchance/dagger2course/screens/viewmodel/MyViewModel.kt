package com.techyourchance.dagger2course.screens.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techyourchance.dagger2course.data.Result
import com.techyourchance.dagger2course.data.usecase.FetchQuestionsUseCase
import com.techyourchance.dagger2course.questions.Question
import kotlinx.coroutines.launch

class MyViewModel (private val fetchQuestionsUseCase: FetchQuestionsUseCase): ViewModel() {

    private val _questionsList: MutableLiveData<Result<List<Question>>> = MutableLiveData()
    val questionsList = _questionsList

    init {
        viewModelScope.launch {
            val response = fetchQuestionsUseCase.fetchLatestQuestions()
           _questionsList.value = response
        }
    }
}