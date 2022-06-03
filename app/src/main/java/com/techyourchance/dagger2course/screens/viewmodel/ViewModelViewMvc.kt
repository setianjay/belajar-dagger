package com.techyourchance.dagger2course.screens.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import com.techyourchance.dagger2course.databinding.ActivityViewModelBinding
import com.techyourchance.dagger2course.screens.common.viewmvc.BaseViewMvc

class ViewModelViewMvc(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
): BaseViewMvc<ActivityViewModelBinding, ViewModelViewMvc.Listener>(
    ActivityViewModelBinding.inflate(layoutInflater, parent, false)
){

    interface Listener{}

    private val tvScreenInfo = binding.tvScreenInfo
}