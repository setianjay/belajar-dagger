package com.techyourchance.dagger2course.data.usecase

import com.techyourchance.dagger2course.data.Result
import com.techyourchance.dagger2course.questions.Question

interface FetchQuestionsUseCase {

    suspend fun fetchLatestQuestions(): Result<List<Question>>
}