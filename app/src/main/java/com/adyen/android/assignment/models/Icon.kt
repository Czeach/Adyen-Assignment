package com.adyen.android.assignment.models


import com.google.gson.annotations.SerializedName

data class Icon(
    @SerializedName("prefix")
    val prefix: String? = null,
    @SerializedName("suffix")
    val suffix: String? = null
)