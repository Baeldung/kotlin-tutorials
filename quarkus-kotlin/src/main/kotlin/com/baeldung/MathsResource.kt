package com.baeldung

import jakarta.json.Json
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path

@Path("/maths")
class MathsResource {
    @Path("/add")
    @POST
    fun add(input: Input) = Json.createValue(input.a + input.b)
}

data class Input(val a: Int, val b: Int)
