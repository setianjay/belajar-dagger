package com.techyourchance.dagger2course.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.techyourchance.dagger2course.data.Result
import com.techyourchance.dagger2course.data.usecase.FetchQuestionsUseCase
import com.techyourchance.dagger2course.screens.common.activities.BaseActivity
import com.techyourchance.dagger2course.screens.common.dialogs.DialogsNavigator
import com.techyourchance.dagger2course.screens.common.screens.ScreensNavigator
import com.techyourchance.dagger2course.screens.common.viewmvc.ViewMvcFactory
import kotlinx.coroutines.*
import javax.inject.Inject

class QuestionDetailsActivity : BaseActivity(), QuestionDetailsViewMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private lateinit var viewMvc: QuestionDetailsViewMvc
    private lateinit var questionId: String
    @Inject lateinit var dialogsNavigator: DialogsNavigator
    @Inject lateinit var fetchQuestionsUseCase: FetchQuestionsUseCase
    @Inject lateinit var screensNavigator: ScreensNavigator
    @Inject lateinit var viewMvcFactory: ViewMvcFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        viewMvc = viewMvcFactory.newQuestionDetailsViewMvc(null)
        setContentView(viewMvc.rootView)

        // retrieve question ID passed from outside
        questionId = intent.extras!!.getString(EXTRA_QUESTION_ID)!!
    }

    override fun onStart() {
        super.onStart()
        fetchQuestionDetails()
        viewMvc.registerListener(this)
    }

    override fun onNavigateBackClicked() {
        screensNavigator.navigateBack()
    }

    override fun onStop() {
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()
        viewMvc.unregisterListener(this)
    }

    private fun fetchQuestionDetails() {
        coroutineScope.launch {
            viewMvc.showProgressIndication()
            try {
                when(val result = fetchQuestionsUseCase.fetchQuestionBody(questionId)){
                    is Result.Success -> {
                        viewMvc.bindQuestionBody(result.data)
                    }
                    is Result.Failure -> {
                        onFetchFailed()
                    }
                }
            } finally {
                viewMvc.hideProgressIndication()
            }

        }
    }

    private fun onFetchFailed() {
        dialogsNavigator.showServerErrorDialog()
    }

    companion object {
        const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"
        fun start(context: Context, questionId: String) {
            val intent = Intent(context, QuestionDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, questionId)
            context.startActivity(intent)
        }
    }

}