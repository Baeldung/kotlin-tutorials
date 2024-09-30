package com.baeldung.koin.dagger.migration

class MyService(private val myRepository: MyRepository) {
    fun performAction(): String {
        return "Service is using: " + myRepository.getData()
    }
}