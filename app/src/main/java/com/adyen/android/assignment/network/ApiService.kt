package com.adyen.android.assignment.network

import com.adyen.android.assignment.models.NearbyPlaces
import com.adyen.android.assignment.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface ApiService {

    @Headers(Constants.ACCEPT, Constants.AUTHORIZATION)
    @GET(Constants.NEARBY_PLACES)
    suspend fun getNearbyPlaces(
        @Query("ll") location: String
    ): NearbyPlaces
}