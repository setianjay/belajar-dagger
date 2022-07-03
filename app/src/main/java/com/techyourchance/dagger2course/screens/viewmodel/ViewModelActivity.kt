package com.techyourchance.dagger2course.screens.viewmodel

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.techyourchance.dagger2course.data.Result
import com.techyourchance.dagger2course.screens.common.activities.BaseActivity
import com.techyourchance.dagger2course.screens.common.viewmodels.MyViewModelFactory
import com.techyourchance.dagger2course.screens.common.viewmvc.ViewMvcFactory
import javax.inject.Inject

class ViewModelActivity : BaseActivity() {
    @Inject
    lateinit var viewMvcFactory: ViewMvcFactory
    @Inject
    lateinit var viewModelFactory: MyViewModelFactory

    private lateinit var viewModelViewMvc: ViewModelViewMvc
    private lateinit var myViewModel: MyViewModel
    private lateinit var myViewModel2: MyViewModel2

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        viewModelViewMvc = viewMvcFactory.newViewModelViewMvc(null)
        setContentView(viewModelViewMvc.rootView)
        myViewModel = ViewModelProvider(this, viewModelFactory)[MyViewModel::class.java]
        myViewModel2 = ViewModelProvider(this, viewModelFactory)[MyViewModel2::class.java]
        setupObserve()
    }

    private fun setupObserve() {
        myViewModel2.questionsList.observe(this) {
            val message = when (it) {
                is Result.Success -> {
                    val dataSize = it.data.size
                    "Total list of questions is $dataSize"
                }
                is Result.Failure -> {
                    "Fail to get information about bunch of list questions"
                }
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun start(context: Context) {
            Intent(context, ViewModelActivity::class.java).also {
                context.startActivity(it)
            }
        }
    }
}