package com.techyourchance.dagger2course.screens.questiondetails

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.techyourchance.dagger2course.databinding.LayoutQuestionDetailsBinding
import com.techyourchance.dagger2course.screens.common.toolbar.MyToolbar
import com.techyourchance.dagger2course.screens.common.viewmvc.BaseViewMvc
import kotlinx.android.synthetic.main.layout_question_details.view.*

class QuestionDetailsViewMvc(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
): BaseViewMvc<QuestionDetailsViewMvc.Listener>(
    LayoutQuestionDetailsBinding.inflate(layoutInflater, parent, false)
) {

    interface Listener{
        fun onNavigateBackClicked()
    }

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

    fun bindQuestionBody(questionBody: String){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtQuestionBody.text = Html.fromHtml(questionBody, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            txtQuestionBody.text = Html.fromHtml(questionBody)
        }
    }
}