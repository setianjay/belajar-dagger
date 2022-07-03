package com.techyourchance.dagger2course.common.composition

import com.techyourchance.dagger2course.data.usecase.FetchQuestionsUseCase
import com.techyourchance.dagger2course.screens.viewmodel.MyViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    @PresentationScope
    fun provideMyViewModel(fetchQuestionsUseCase: FetchQuestionsUseCase): MyViewModel =
        MyViewModel(fetchQuestionsUseCase)
}