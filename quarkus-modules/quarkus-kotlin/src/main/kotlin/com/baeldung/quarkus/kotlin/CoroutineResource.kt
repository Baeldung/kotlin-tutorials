package com.baeldung.quarkus.kotlin

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import kotlinx.coroutines.delay

@Path("/coroutine")
class CoroutineResource {
    @GET
    suspend fun get() : String {
        return doSomething()
    }

    suspend fun doSomething() : String {
        Exception().printStackTrace()
        delay(1000)
        return "Hello, World!"
    }
}