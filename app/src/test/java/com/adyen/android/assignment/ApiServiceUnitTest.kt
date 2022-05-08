package com.adyen.android.assignment

import com.adyen.android.assignment.models.NearbyPlaces
import com.adyen.android.assignment.network.ApiService
import com.google.gson.Gson
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ExperimentalCoroutinesApi
class ApiServiceUnitTest {

    lateinit var mockWebServer: MockWebServer

    @Mock
    private lateinit var apiService: ApiService

    lateinit var gson: Gson

    @Before
    fun initMocks() {
        MockitoAnnotations.initMocks(this)
        gson = Gson()
        mockWebServer = MockWebServer()

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ApiService::class.java)

    }

    @Test
    fun testApiService() = runBlocking {
        val mockResponse = MockResponse()

        mockWebServer.enqueue(mockResponse.setBody("{}"))

        val location = ""

        val response = apiService.getNearbyPlaces(location)

        val request = mockWebServer.takeRequest()

        val channel = Channel<Response<NearbyPlaces>>()

        val job = launch {
            channel.send(response)
        }

        assertEquals("/nearby?ll=$location",request.path)
        assertEquals(200, response.code())
        assertEquals(NearbyPlaces(), response.body())
        job.cancel()
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}