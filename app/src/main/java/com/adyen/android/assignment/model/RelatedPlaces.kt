package com.adyen.android.assignment.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RelatedPlaces(
    @SerialName("parent")
    val parent: Parent? = null,
    @SerialName("children")
    val children: List<Children>? = null
)