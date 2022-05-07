package com.adyen.android.assignment.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Geocodes(
    @SerialName("main")
    val main: Main? = null
)