package com.adyen.android.assignment.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Icon(
    @SerialName("prefix")
    val prefix: String?= null,
    @SerialName("suffix")
    val suffix: String?= null
)