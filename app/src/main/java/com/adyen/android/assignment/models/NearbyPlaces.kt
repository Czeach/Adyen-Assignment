package com.adyen.android.assignment.models


import com.google.gson.annotations.SerializedName

data class NearbyPlaces(
    @SerializedName("results")
    val results: List<Result>? = null,
    @SerializedName("message")
    val message: String? = null
)