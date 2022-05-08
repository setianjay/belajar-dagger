package com.techyourchance.dagger2course.screens.common.viewmvc

import android.content.Context
import android.view.View
import androidx.viewbinding.ViewBinding

open class BaseViewMvc<LAYOUT_TYPE : ViewBinding, LISTENER_TYPE>(
    protected val binding: LAYOUT_TYPE
) {

    val rootView: View = binding.root
    protected val context: Context get() = rootView.context
    protected val listeners = HashSet<LISTENER_TYPE>()

    fun registerListener(listener: LISTENER_TYPE) {
        listeners.add(listener)
    }

    fun unregisterListener(listener: LISTENER_TYPE) {
        listeners.remove(listener)
    }
}