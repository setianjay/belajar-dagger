package com.techyourchance.dagger2course.questions

import com.google.gson.annotations.SerializedName
import com.techyourchance.dagger2course.model.Owner

data class QuestionWithBody(
        @SerializedName("title") val title: String,
        @SerializedName("question_id") val id: String,
        @SerializedName("body") val body: String,
        @SerializedName("owner") val owner: Owner
)