package com.adyen.android.assignment.models


import com.google.gson.annotations.SerializedName

data class RelatedPlaces(
    @SerializedName("parent")
    val parent: Parent? = null,
    @SerializedName("children")
    val children: List<Children>? = null
)