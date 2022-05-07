package com.adyen.android.assignment.model


import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerialName("fsq_id")
    val fsqId: String? = null,
    @SerialName("categories")
    val categories: List<Category>? = null,
    @SerialName("distance")
    val distance: Int? = null,
    @SerialName("geocodes")
    val geocodes: Geocodes? = null,
    @SerialName("link")
    val link: String? = null,
    @SerialName("location")
    val location: Location? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("related_places")
    val relatedPlaces: RelatedPlaces? = null,
    @SerialName("timezone")
    val timezone: String? = null
)