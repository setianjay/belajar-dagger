package com.techyourchance.dagger2course.screens.common.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.techyourchance.dagger2course.screens.viewmodel.MyViewModel
import javax.inject.Inject
import javax.inject.Provider

class MyViewModelFactory @Inject constructor(
    private val myViewModel: Provider<MyViewModel>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MyViewModel::class.java -> myViewModel.get() as T
            else -> throw RuntimeException("view model class is not found")
        }
    }
}