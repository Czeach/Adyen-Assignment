package com.adyen.android.assignment.repository

import com.adyen.android.assignment.models.Result
import com.adyen.android.assignment.network.ApiService
import com.adyen.android.assignment.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class NearbyPlacesRepository(
    private val apiService: ApiService
) {
    fun getNearbyPlaces(
        location: String
    ): Flow<DataState<List<Result>>> {
        return flow<DataState<List<Result>>> {

            emit(DataState.loading())

            val response = apiService.getNearbyPlaces(location)

            try {
                if (response.results == null) {
                    emit(DataState.error(message = response.message.toString()))
                } else {
                    emit(DataState.data(data = response.results))
                }
            } catch (e: Exception) {
                emit(
                    DataState.error(
                        message = response.message ?: e.message.toString()
                    )
                )
            }
        }.flowOn(Dispatchers.IO)
    }
}