package com.baeldung.thymeleaf.server.plugins

import com.baeldung.thymeleaf.data.DataHolder
import com.baeldung.thymeleaf.data.GradeValue
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.thymeleaf.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respond(ThymeleafContent("index", mapOf("studentList" to DataHolder.getStudentList())))
        }
        get("/report-card/{id}") {
            call.respond(
                ThymeleafContent("report-card",
                    mapOf(
                        "student" to DataHolder.findStudentById(call.parameters["id"]),
                        "gradeOptionList" to GradeValue.entries
                    )
                )
            )
        }
        post("/report-card/{id}") {
            val parameters = call.receiveParameters()
            DataHolder.updateGrades(call.parameters["id"], parameters)
            call.respondRedirect("/", false)
        }
    }
}