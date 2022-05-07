package com.adyen.android.assignment.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    @SerialName("address")
    val address: String? = null,
    @SerialName("country")
    val country: String? = null,
    @SerialName("cross_street")
    val crossStreet: String? = null,
    @SerialName("formatted_address")
    val formattedAddress: String? = null,
    @SerialName("locality")
    val locality: String? = null,
    @SerialName("postcode")
    val postcode: String? = null,
    @SerialName("region")
    val region: String? = null
)