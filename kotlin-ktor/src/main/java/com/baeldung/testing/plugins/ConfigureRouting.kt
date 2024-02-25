package com.baeldung.testing.plugins

import com.baeldung.testing.data.Car
import com.baeldung.testing.data.CarStorageMock
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        route("cars") {
        get {
            call.respond(CarStorageMock.carStorage)
        }
        get("{id?}") {
            val id = call.parameters["id"]
            val car = CarStorageMock.carStorage.find { it.id == id } ?: return@get call.respondText(
                text = "car.not.found",
                status = HttpStatusCode.NotFound
            )
            call.respond(car)
        }
        post {
            val car = call.receive<Car>()
            CarStorageMock.carStorage.add(car)
            call.respond(status = HttpStatusCode.Created, message = car)
        }
        put("{id?}") {
            val id = call.parameters["id"]
            val car = CarStorageMock.carStorage.find { it.id == id } ?: return@put call.respondText(
                text = "car.not.found",
                status = HttpStatusCode.NotFound
            )
            val carUpdate = call.receive<Car>()
            car.brand = carUpdate.brand
            car.price = carUpdate.price
            call.respond(car)
        }
        delete("{id?}") {
            val id = call.parameters["id"]
            if (CarStorageMock.carStorage.removeIf { it.id == id }) {
                call.respondText(text = "car.deleted", status = HttpStatusCode.OK)
            } else {
                call.respondText(text = "car.not.found", status = HttpStatusCode.NotFound)
            }
        }
        }
    }
}