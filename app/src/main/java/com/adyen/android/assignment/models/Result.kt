package com.adyen.android.assignment.models


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("fsq_id")
    val fsqId: String? = null,
    @SerializedName("categories")
    val categories: List<Category>? = null,
    @SerializedName("chains")
    val chains: List<Any>? = null,
    @SerializedName("distance")
    val distance: Int? = null,
    @SerializedName("geocodes")
    val geocodes: Geocodes? = null,
    @SerializedName("link")
    val link: String? = null,
    @SerializedName("location")
    val location: Location? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("related_places")
    val relatedPlaces: RelatedPlaces? = null,
    @SerializedName("timezone")
    val timezone: String? = null
)