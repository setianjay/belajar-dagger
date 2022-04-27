package com.techyourchance.dagger2course.data.usecase

import com.techyourchance.dagger2course.Constants
import com.techyourchance.dagger2course.data.Result
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.questions.Question
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FetchQuestionsUseCaseImpl: FetchQuestionsUseCase {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val stackoverflowApi: StackoverflowApi = retrofit.create(StackoverflowApi::class.java)


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
}