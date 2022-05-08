package com.adyen.android.assignment.utils

import com.adyen.android.assignment.BuildConfig

object Constants {

    const val PERMISSION_ID = 25

    const val BASE_URL = "https://api.foursquare.com/v3/places/"

    const val NEARBY_PLACES = "nearby"

    const val AUTHORIZATION = "Authorization: ${BuildConfig.API_KEY}"

    const val ACCEPT = "Accept: application/json"
}