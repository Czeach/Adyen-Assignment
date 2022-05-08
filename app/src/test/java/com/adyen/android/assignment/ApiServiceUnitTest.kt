package com.adyen.android.assignment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.adyen.android.assignment.models.NearbyPlaces
import com.adyen.android.assignment.network.ApiService
import com.google.gson.Gson
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class ApiServiceUnitTest {


    @get: Rule
    val instantTaskExecutorRule: org.junit.rules.TestRule = InstantTaskExecutorRule()

    @get: Rule
    val testCoroutineRule = TestRule()

    lateinit var mockWebServer: MockWebServer

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

        assertEquals("/nearby?ll=$location",request.path)
        assertEquals(200, response.code())
        assertEquals(NearbyPlaces(), response.body())
    }
}