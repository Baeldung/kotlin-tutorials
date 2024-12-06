package com.baeldung.dagger

import javax.inject.Inject
import javax.inject.Named

class NetworkClient @Inject constructor(private val apiService: ApiService)

class NetworkClientMultipleInstances @Inject constructor(
    @Named("debug") private val debugApiService: ApiService,
    @Named("release") private val releaseApiService: ApiService
) {
    fun getDebugData() = debugApiService.getData()
    fun getReleaseData() = releaseApiService.getData()
}