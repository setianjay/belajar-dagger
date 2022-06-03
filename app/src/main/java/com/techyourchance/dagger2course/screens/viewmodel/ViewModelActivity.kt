package com.techyourchance.dagger2course.screens.viewmodel

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.techyourchance.dagger2course.screens.common.activities.BaseActivity
import com.techyourchance.dagger2course.screens.common.viewmvc.ViewMvcFactory
import javax.inject.Inject

class ViewModelActivity : BaseActivity() {
    @Inject lateinit var viewMvcFactory: ViewMvcFactory

    private lateinit var viewModelViewMvc: ViewModelViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        viewModelViewMvc = viewMvcFactory.newViewModelViewMvc(null)
        setContentView(viewModelViewMvc.rootView)
    }

    companion object{
        fun start(context: Context){
            Intent(context, ViewModelActivity::class.java).also {
                context.startActivity(it)
            }
        }
    }
}