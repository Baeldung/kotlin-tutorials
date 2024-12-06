package com.baeldung.quarkus.kotlin

import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path

@Path("/inject")
class InjectResource @Inject constructor(private val injectableService: InjectableService) {

    @GET
    fun handler() = injectableService.greeting()
}