package com.adyen.android.assignment.models


import com.google.gson.annotations.SerializedName

data class Geocodes(
    @SerializedName("main")
    val main: Main? = null
)