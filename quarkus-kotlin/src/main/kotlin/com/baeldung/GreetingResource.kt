package com.baeldung

import jakarta.json.Json
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path

@Path("/hello")
class GreetingResource {

    @GET
    fun hello() = Json.createValue("Hello, World!")
}
