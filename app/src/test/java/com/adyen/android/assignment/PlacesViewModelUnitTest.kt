package com.adyen.android.assignment


import com.adyen.android.assignment.models.Result
import com.adyen.android.assignment.repository.NearbyPlacesRepository
import com.adyen.android.assignment.ui.nearbyPlaces.NearbyPlacesViewModel
import com.adyen.android.assignment.utils.DataState
import com.adyen.android.assignment.utils.NearbyPlacesState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class PlacesViewModelUnitTest {

    @get: Rule
    val testCoroutineRule = TestRule()

    @Mock
    private lateinit var nearbyPlacesRepository: NearbyPlacesRepository

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun initMocks(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testNearbyPlacesViewModel() = testCoroutineDispatcher.runBlockingTest {

        val nearbyPlacesViewModel = NearbyPlacesViewModel(nearbyPlacesRepository)

        val location = ""

        val data = Result()

        val response = DataState.data("", listOf(data))

        val channel = Channel<DataState<List<Result>>>()

        val flow = channel.consumeAsFlow()

        Mockito.`when`(nearbyPlacesRepository.getNearbyPlaces(location)).thenReturn(flow)

        nearbyPlacesViewModel.getNearbyPlaces(location)

        val job = launch {
            channel.send(response)
        }

        Assert.assertEquals(true, nearbyPlacesViewModel.nearbyPlacesState.value == NearbyPlacesState.Success(listOf(data)))
        Assert.assertEquals(false, nearbyPlacesViewModel.nearbyPlacesState.value == NearbyPlacesState.Loading)
        Assert.assertEquals(false, nearbyPlacesViewModel.nearbyPlacesState.value == NearbyPlacesState.Error(""))
        job.cancel()
    }
}
