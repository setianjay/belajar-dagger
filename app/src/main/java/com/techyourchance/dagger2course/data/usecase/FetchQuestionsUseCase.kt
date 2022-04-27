package com.techyourchance.dagger2course.data.usecase

import com.techyourchance.dagger2course.data.Result
import com.techyourchance.dagger2course.questions.Question
import com.techyourchance.dagger2course.questions.QuestionWithBody

interface FetchQuestionsUseCase {

    suspend fun fetchLatestQuestions(): Result<List<Question>>

    suspend fun fetchQuestionBody(questionId: String): Result<QuestionWithBody>
}