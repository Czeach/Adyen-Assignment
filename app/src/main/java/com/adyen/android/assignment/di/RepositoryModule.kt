package com.adyen.android.assignment.di

import com.adyen.android.assignment.network.ApiService
import com.adyen.android.assignment.repository.NearbyPlacesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNearbyPlacesRepository(
        apiService: ApiService
    ): NearbyPlacesRepository {
        return NearbyPlacesRepository(
            apiService = apiService
        )
    }
}