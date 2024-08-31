package com.baeldung.crud.versioning.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PathBasedGreetingController {

    @GetMapping("/v1/greeting")
    fun greetingV1(): String {
        return "Hello, welcome to the API v1!"
    }

    @GetMapping("/v2/greeting")
    fun greetingV2(): String {
        return "Hello, welcome to the API v2!"
    }
}