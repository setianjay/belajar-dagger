package com.techyourchance.dagger2course.screens.questionslist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.MyApplication
import com.techyourchance.dagger2course.data.Result
import com.techyourchance.dagger2course.data.usecase.FetchQuestionsUseCase
import com.techyourchance.dagger2course.data.usecase.FetchQuestionsUseCaseImpl
import com.techyourchance.dagger2course.questions.Question
import com.techyourchance.dagger2course.screens.common.activities.BaseActivity
import com.techyourchance.dagger2course.screens.common.dialogs.DialogsNavigator
import com.techyourchance.dagger2course.screens.common.screens.ScreensNavigator
import com.techyourchance.dagger2course.screens.common.viewmvc.ViewMvcFactory
import kotlinx.coroutines.*

class QuestionsListActivity : BaseActivity(), QuestionsListViewMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private lateinit var viewMvc: QuestionsListViewMvc
    private var isDataLoaded = false
    lateinit var dialogsNavigator: DialogsNavigator
    lateinit var fetchQuestionsUseCase: FetchQuestionsUseCase
    lateinit var screensNavigator: ScreensNavigator
    lateinit var viewMvcFactory: ViewMvcFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        viewMvc = viewMvcFactory.newQuestionsListViewMvc(null)
        setContentView(viewMvc.rootView)
    }

    override fun onStart() {
        super.onStart()
        if (!isDataLoaded) {
            fetchQuestions()
        }
        viewMvc.registerListener(this)
    }

    override fun onRefreshClicked() {
        fetchQuestions()
    }

    override fun onQuestionClicked(question: Question) {
        screensNavigator.toDetailsQuestion(question.id)
    }

    override fun onStop() {
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()
        viewMvc.unregisterListener(this)
    }

    private fun fetchQuestions() {
        coroutineScope.launch {
            viewMvc.showProgressIndication()
            try {
                when(val result = fetchQuestionsUseCase.fetchLatestQuestions()){
                    is Result.Failure -> {
                        onFetchFailed()
                    }
                    is Result.Success -> {
                        viewMvc.bindQuestions(result.data)
                        isDataLoaded = true
                    }
                }
            }
            finally {
               viewMvc.hideProgressIndication()
            }
        }
    }

    private fun onFetchFailed() {
        dialogsNavigator.showServerErrorDialog()
    }
}