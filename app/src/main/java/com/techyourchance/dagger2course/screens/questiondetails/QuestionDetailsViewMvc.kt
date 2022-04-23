package com.techyourchance.dagger2course.screens.questiondetails

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewbinding.ViewBinding
import com.techyourchance.dagger2course.databinding.LayoutQuestionDetailsBinding
import com.techyourchance.dagger2course.screens.common.toolbar.MyToolbar
import kotlinx.android.synthetic.main.layout_question_details.view.*

class QuestionDetailsViewMvc(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) {

    interface Listener{
        fun onNavigateBackClicked()
    }
    private val listeners = HashSet<Listener>()
    private val binding: ViewBinding =
        LayoutQuestionDetailsBinding.inflate(layoutInflater, parent, false)
    val rootView: View = binding.root

    private val myToolbar: MyToolbar = rootView.toolbar
    private val swiperRefreshLayout: SwipeRefreshLayout = rootView.swipeRefresh
    private val txtQuestionBody: TextView = rootView.txt_question_body

    init {
        myToolbar.setNavigateUpListener {
            for (listener in listeners){
                listener.onNavigateBackClicked()
            }
        }

        swiperRefreshLayout.isEnabled = false
    }

    fun showProgressIndication() {
        swiperRefreshLayout.isRefreshing = true
    }

    fun hideProgressIndication() {
        swiperRefreshLayout.isRefreshing = false
    }

    fun registerListener(listener: Listener){
        listeners.add(listener)
    }

    fun unregisterListener(listener: Listener){
        listeners.remove(listener)
    }

    fun bindQuestionBody(questionBody: String){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtQuestionBody.text = Html.fromHtml(questionBody, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            txtQuestionBody.text = Html.fromHtml(questionBody)
        }
    }
}