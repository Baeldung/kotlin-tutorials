package com.baeldung.koin.dagger.migration

import jakarta.inject.Inject

class MyService(private val myRepository: MyRepository) {
    fun performAction(): String {
        return "Service is using: " + myRepository.getData()
    }
}

class MyServiceDagger @Inject constructor(private val myRepository: MyRepository) {
    fun performAction(): String {
        return "Service is using: " + myRepository.getData()
    }
}