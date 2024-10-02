package com.baeldung.springreactivekotlin

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromValue
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class HomeSensorsHandler {
    var data = mapOf("lamp" to arrayOf(0.7, 0.65, 0.67), "fridge" to arrayOf(12.0, 11.9, 12.5))

    fun setLight(request: ServerRequest): Mono<ServerResponse> = ServerResponse.ok().build()

    fun getLightReading(request: ServerRequest): Mono<ServerResponse> = ServerResponse.ok().body(fromValue(data["lamp"]!!))

    fun getDeviceReadings(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id")
        return ServerResponse.ok().body(fromValue(Device(id, 1.0)))
    }

    fun getAllDevices(request: ServerRequest): Mono<ServerResponse> = ServerResponse.ok().body(fromValue(arrayOf("lamp", "tv")))

    fun getAllDeviceApi(request: ServerRequest): Mono<ServerResponse> = ServerResponse.ok().body(fromValue(arrayListOf("kettle", "fridge")))

    fun setDeviceReadingApi(request: ServerRequest): Mono<ServerResponse> {
        return request.bodyToMono(Device::class.java).flatMap {
            ServerResponse.ok().body(fromValue(Device(it.name.uppercase(), it.reading)))
        }
    }
}
