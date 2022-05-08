package com.adyen.android.assignment.ui.nearbyPlaces

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adyen.android.assignment.repository.NearbyPlacesRepository
import com.adyen.android.assignment.utils.NearbyPlacesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NearbyPlacesViewModel @Inject constructor(
    private val nearbyPlacesRepository: NearbyPlacesRepository
) : ViewModel() {

    val nearbyPlacesState = MutableStateFlow<NearbyPlacesState?>(null)

    fun getNearbyPlaces(location: String) {
        viewModelScope.launch {
            nearbyPlacesRepository.getNearbyPlaces(location).collect {
                when {
                    it.isLoading -> {
                        nearbyPlacesState.value = NearbyPlacesState.Loading
                    }
                    it.data == null -> {
                        nearbyPlacesState.value = NearbyPlacesState.Error(message = it.message.toString())
                    }
                    else -> {
                        it.data.let { data ->
                            nearbyPlacesState.value = NearbyPlacesState.Success(data = data)
                        }
                    }
                }
            }
        }
    }
}