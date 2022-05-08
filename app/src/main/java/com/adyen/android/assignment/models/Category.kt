package com.adyen.android.assignment.models


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("icon")
    val icon: Icon? = null
)