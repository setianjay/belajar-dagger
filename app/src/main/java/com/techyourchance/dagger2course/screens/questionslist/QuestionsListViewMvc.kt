package com.techyourchance.dagger2course.screens.questionslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewbinding.ViewBinding
import com.techyourchance.dagger2course.R
import com.techyourchance.dagger2course.databinding.LayoutQuestionsListBinding
import com.techyourchance.dagger2course.questions.Question
import kotlinx.android.synthetic.main.layout_questions_list.view.*
import java.util.ArrayList

class QuestionsListViewMvc(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) {
    interface Listener {
        fun onRefreshClicked()
        fun onQuestionClicked(question: Question)
    }

    private val listeners = HashSet<Listener>()

    private val binding: ViewBinding =
        LayoutQuestionsListBinding.inflate(layoutInflater, parent, false)
    val rootView = binding.root

    private val recyclerView: RecyclerView = rootView.recycler
    private val swipeRefreshLayout: SwipeRefreshLayout = rootView.swipeRefresh
    private val questionsAdapter: QuestionsAdapter = QuestionsAdapter { question: Question ->
        for (listener in listeners){
            listener.onQuestionClicked(question)
        }
    }

    init {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(rootView.context)
            adapter = questionsAdapter
            setHasFixedSize(true)
        }

        swipeRefreshLayout.setOnRefreshListener {
            for (listener in listeners){
                listener.onRefreshClicked()
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

    fun registerListener(listener: Listener){
        listeners.add(listener)
    }

    fun unregisterListener(listener: Listener){
        listeners.remove(listener)
    }

    fun bindData(questions: List<Question>) {
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