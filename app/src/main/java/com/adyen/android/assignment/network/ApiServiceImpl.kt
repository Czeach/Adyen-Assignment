package com.adyen.android.assignment.network

import android.annotation.SuppressLint
import android.util.Log
import com.adyen.android.assignment.BuildConfig
import com.adyen.android.assignment.model.NearbyPlaces
import com.adyen.android.assignment.utils.Constants
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

class ApiServiceImpl(
    private val client: HttpClient,
    private val baseUrl: String
): ApiService {

    @SuppressLint("LongLogTag")
    override suspend fun getNearbyPlaces(location: String): NearbyPlaces {
        return try {
            client.get {
                Url(Constants.BASE_URL)
                accept(ContentType.Application.Json)
                header(HttpHeaders.Authorization, BuildConfig.API_KEY)
            }
        } catch (e: ClientRequestException) {
            Log.e("DataClientRequestException", "Error: ${e.response.status}")
            NearbyPlaces(
                message = e.response.status.toString()
            )
        } catch (e: ServerResponseException) {
            Log.e("DataServerResponseException", "Error: ${e.response.status}")
            NearbyPlaces(
                message = e.response.status.toString()
            )
        } catch (e: RedirectResponseException) {
            Log.e("DataRedirectResponseException", "Error: ${e.response.status}")
            NearbyPlaces(
                message = e.response.status.toString()
            )
        }
    }
}