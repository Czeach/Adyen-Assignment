package com.adyen.android.assignment.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NearbyPlaces(
    @SerialName("results")
    val results: List<Result>? = null,
    @SerialName("message")
    val message: String? = null
)
