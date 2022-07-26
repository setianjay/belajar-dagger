package com.techyourchance.dagger2course.util

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * view extension function
 * */

fun ImageView.load(url: String){
    Glide.with(this)
        .load(url)
        .centerCrop()
        .into(this)
}