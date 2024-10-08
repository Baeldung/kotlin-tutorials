package com.baeldung.quarkus.kotlin

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path

@Path("/hello")
class GreetingResource {

    @GET
    fun hello() = Greeting("Hello, World!")
}

data class Greeting(val greeting: String)