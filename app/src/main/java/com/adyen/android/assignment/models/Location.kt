package com.adyen.android.assignment.models


import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("address")
    val address: String? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("cross_street")
    val crossStreet: String? = null,
    @SerializedName("formatted_address")
    val formattedAddress: String? = null,
    @SerializedName("locality")
    val locality: String? = null,
    @SerializedName("postcode")
    val postcode: String? = null,
    @SerializedName("region")
    val region: String? = null
)