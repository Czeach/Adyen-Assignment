package com.adyen.android.assignment.repository

import com.adyen.android.assignment.models.Result
import com.adyen.android.assignment.network.ApiService
import com.adyen.android.assignment.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException

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
                if (!response.isSuccessful) {
                    emit(DataState.error(message = "Error ${response.code()}"))
                } else {
                    emit(DataState.data(data = response.body()?.results))
                }
            } catch (e: HttpException) {
                emit(
                    DataState.error(
                        message = e.code().toString()
                    )
                )
            }
        }.flowOn(Dispatchers.IO)
    }
}