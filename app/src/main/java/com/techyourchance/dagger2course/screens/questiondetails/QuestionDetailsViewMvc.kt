package com.techyourchance.dagger2course.screens.questiondetails

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.techyourchance.dagger2course.databinding.LayoutQuestionDetailsBinding
import com.techyourchance.dagger2course.questions.QuestionWithBody
import com.techyourchance.dagger2course.screens.common.toolbar.MyToolbar
import com.techyourchance.dagger2course.screens.common.viewmvc.BaseViewMvc
import com.techyourchance.dagger2course.util.load

class QuestionDetailsViewMvc(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : BaseViewMvc<LayoutQuestionDetailsBinding, QuestionDetailsViewMvc.Listener>(
    LayoutQuestionDetailsBinding.inflate(layoutInflater, parent, false)
) {

    interface Listener {
        fun onNavigateBackClicked()
    }

    private val myToolbar: MyToolbar = binding.toolbar
    private val swiperRefreshLayout: SwipeRefreshLayout = binding.swipeRefresh
    private val ivOwner: ImageView = binding.ivOwner
    private val txtOwnerName: TextView = binding.tvOwnerName
    private val txtQuestionBody: TextView = binding.txtQuestionBody

    init {
        myToolbar.setNavigateUpListener {
            for (listener in listeners) {
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

    fun bindQuestionBody(questionBody: QuestionWithBody) {
        val owner = questionBody.owner
        val question = questionBody.body

        ivOwner.load(owner.ownerImage)
        txtOwnerName.text = owner.ownerName
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtQuestionBody.text = Html.fromHtml(question, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            txtQuestionBody.text = Html.fromHtml(question)
        }
    }
}