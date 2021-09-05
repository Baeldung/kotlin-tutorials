package com.baeldung.logfilter

import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux

@RestController
class Controller {

    @GetMapping(path = ["/get"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun get(): Flux<Int> {
        return Flux.range(1, 3)
    }

    data class IntRequest(val input: Int)

    @PostMapping(path = ["/post"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun post(@RequestBody intRequest: IntRequest): Flux<Int> {
        return Flux.range(1, 3)
    }

}
