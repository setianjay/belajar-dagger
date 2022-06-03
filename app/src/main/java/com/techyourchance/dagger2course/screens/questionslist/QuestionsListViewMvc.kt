package com.techyourchance.dagger2course.screens.questionslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.techyourchance.dagger2course.R
import com.techyourchance.dagger2course.databinding.LayoutQuestionsListBinding
import com.techyourchance.dagger2course.questions.Question
import com.techyourchance.dagger2course.screens.common.toolbar.MyToolbar
import com.techyourchance.dagger2course.screens.common.viewmvc.BaseViewMvc

class QuestionsListViewMvc(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : BaseViewMvc<LayoutQuestionsListBinding, QuestionsListViewMvc.Listener>(
    LayoutQuestionsListBinding.inflate(layoutInflater, parent, false)
) {
    interface Listener {
        fun onRefreshClicked()
        fun onQuestionClicked(question: Question)
        fun onViewModelClicked()
    }

    private val recyclerView: RecyclerView = binding.recycler
    private val swipeRefreshLayout: SwipeRefreshLayout = binding.swipeRefresh
    private val toolbar: MyToolbar = binding.toolbar
    private val questionsAdapter: QuestionsAdapter = QuestionsAdapter { question: Question ->
        for (listener in listeners) {
            listener.onQuestionClicked(question)
        }
    }

    init {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = questionsAdapter
            setHasFixedSize(true)
        }

        swipeRefreshLayout.setOnRefreshListener {
            for (listener in listeners) {
                listener.onRefreshClicked()
            }
        }

        toolbar.setViewModelListener {
            for (listener in listeners){
                listener.onViewModelClicked()
            }
        }
    }

    fun showProgressIndication() {
        swipeRefreshLayout.isRefreshing = true
    }

    fun hideProgressIndication() {
        if (swipeRefreshLayout.isRefreshing) {
            swipeRefreshLayout.isRefreshing = false
        }
    }

    fun bindQuestions(questions: List<Question>) {
        questionsAdapter.bindData(questions)
    }

    class QuestionsAdapter(
        private val onQuestionClickListener: (Question) -> Unit
    ) : RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder>() {

        private var questionsList: List<Question> = ArrayList(0)

        inner class QuestionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val title: TextView = view.findViewById(R.id.txt_title)
        }

        fun bindData(questions: List<Question>) {
            questionsList = ArrayList(questions)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_question_list_item, parent, false)
            return QuestionViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
            holder.title.text = questionsList[position].title
            holder.itemView.setOnClickListener {
                onQuestionClickListener.invoke(questionsList[position])
            }
        }

        override fun getItemCount(): Int {
            return questionsList.size
        }

    }
}