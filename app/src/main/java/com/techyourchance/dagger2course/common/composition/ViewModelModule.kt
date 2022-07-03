package com.techyourchance.dagger2course.common.composition

import androidx.lifecycle.ViewModel
import com.techyourchance.dagger2course.data.usecase.FetchQuestionsUseCase
import com.techyourchance.dagger2course.screens.viewmodel.MyViewModel
import com.techyourchance.dagger2course.screens.viewmodel.MyViewModel2
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ViewModelModule {

    @Provides
    @IntoMap
    @ViewModelKey(MyViewModel::class)
    fun provideMyViewModel(fetchQuestionsUseCase: FetchQuestionsUseCase): ViewModel =
        MyViewModel(fetchQuestionsUseCase)

    @Provides
    @IntoMap
    @ViewModelKey(MyViewModel2::class)
    fun provideMyViewModel2(fetchQuestionsUseCase: FetchQuestionsUseCase): ViewModel =
        MyViewModel2(fetchQuestionsUseCase)
}