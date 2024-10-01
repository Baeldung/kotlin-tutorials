package com.baeldung

import jakarta.ws.rs.DefaultValue
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.QueryParam

@Path("/params")
class ParamsResource {
    @GET
    @Path("/greet/{name}")
    fun greetPath(@PathParam("name") name: String) = "Hello, ${name}!"

    @GET
    @Path("/greet")
    fun greetQuery(@QueryParam("name") @DefaultValue("Baeldung") name: String) = "Hello, ${name}"
}