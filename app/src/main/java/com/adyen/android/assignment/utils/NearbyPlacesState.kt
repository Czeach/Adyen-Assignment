package com.adyen.android.assignment.utils

import com.adyen.android.assignment.model.Result

sealed class NearbyPlacesState {
    data class Success(val data: List<Result>?): NearbyPlacesState()
    data class Error(val message: String): NearbyPlacesState()
    object Loading: NearbyPlacesState()
}