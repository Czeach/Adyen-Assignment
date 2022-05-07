package com.adyen.android.assignment.network

import com.adyen.android.assignment.model.NearbyPlaces

interface ApiService {

    suspend fun getNearbyPlaces(location: String): NearbyPlaces
}