package com.techyourchance.dagger2course.data.usecase

import com.techyourchance.dagger2course.data.Result
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.questions.Question
import com.techyourchance.dagger2course.questions.QuestionWithBody
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchQuestionsUseCaseImpl(private val stackoverflowApi: StackoverflowApi): FetchQuestionsUseCase {

    override suspend fun fetchLatestQuestions(): Result<List<Question>> {
        return withContext(Dispatchers.IO){
            try {
                val response = stackoverflowApi.lastActiveQuestions(20)
                if (response.isSuccessful && response.body() != null){
                    return@withContext Result.Success(response.body()!!.questions)
                }else{
                    return@withContext Result.Failure
                }
            }catch (t: Throwable){
                if (t != CancellationException()){
                    return@withContext Result.Failure
                }else{
                    throw t
                }
            }
        }
    }

    override suspend fun fetchQuestionBody(questionId: String): Result<QuestionWithBody> {
        return withContext(Dispatchers.IO){
            try {
                val response = stackoverflowApi.questionDetails(questionId)
                if (response.isSuccessful && response.body() != null){
                    return@withContext Result.Success(response.body()!!.question)
                }else{
                    return@withContext Result.Failure
                }
            }catch (t: Throwable){
                if (t != CancellationException()){
                    return@withContext Result.Failure
                }else{
                    throw t
                }
            }
        }
    }
}