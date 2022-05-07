package com.adyen.android.assignment.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Parent(
    @SerialName("fsq_id")
    val fsqId: String? = null,
    @SerialName("name")
    val name: String? = null
)