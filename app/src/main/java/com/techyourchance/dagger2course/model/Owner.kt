package com.techyourchance.dagger2course.model

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("display_name") val ownerName: String,
    @SerializedName("profile_image") val ownerImage: String
)
