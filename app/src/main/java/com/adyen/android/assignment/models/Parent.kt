package com.adyen.android.assignment.models


import com.google.gson.annotations.SerializedName

data class Parent(
    @SerializedName("fsq_id")
    val fsqId: String? = null,
    @SerializedName("name")
    val name: String? = null
)