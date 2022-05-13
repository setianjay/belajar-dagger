package com.techyourchance.dagger2course.common.composition

import com.techyourchance.dagger2course.data.usecase.FetchQuestionsUseCase
import com.techyourchance.dagger2course.data.usecase.FetchQuestionsUseCaseImpl
import com.techyourchance.dagger2course.networking.StackoverflowApi
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun fetchQuestionsUseCase(stackoverflowApi: StackoverflowApi): FetchQuestionsUseCase = FetchQuestionsUseCaseImpl(stackoverflowApi)
}