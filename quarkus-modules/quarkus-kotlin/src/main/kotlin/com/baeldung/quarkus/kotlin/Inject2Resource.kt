package com.baeldung.quarkus.kotlin

import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path

@Path("/inject2")
class Inject2Resource {
    @Inject
    lateinit var injectableService: InjectableService

    @GET
    fun handler() = injectableService.greeting()
}