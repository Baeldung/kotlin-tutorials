package com.baeldung.kotlinjee.rest

import com.baeldung.kotlinjee.entity.Student
import com.baeldung.kotlinjee.service.StudentService
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/student")
open class StudentResource {

    @Inject
    private lateinit var service: StudentService

    @POST
    open fun create(student: Student): Response {
        service.create(student)
        return Response.ok().build()
    }

    @GET
    @Path("/{id}")
    open fun read(@PathParam("id") id: Long): Response {
        val student  = service.read(id)
        return Response.ok(student, MediaType.APPLICATION_JSON_TYPE).build()
    }

    @PUT
    open fun update(student: Student): Response {
        service.update(student)
        return Response.ok(student, MediaType.APPLICATION_JSON_TYPE).build()
    }

    @DELETE
    @Path("/{id}")
    open fun delete(@PathParam("id") id: Long): Response {
        service.delete(id)
        return Response.noContent().build()
    }

}