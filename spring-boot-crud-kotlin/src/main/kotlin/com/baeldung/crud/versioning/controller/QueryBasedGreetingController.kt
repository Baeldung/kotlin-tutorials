package com.baeldung.crud.versioning.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class QueryBasedGreetingController {

    @GetMapping("/greeting", params = ["version=1"])
    fun greetingV1(): String {
        return "Hello, welcome to the API v1!"
    }

    @GetMapping("/greeting", params = ["version=2"])
    fun greetingV2(): String {
        return "Hello, welcome to the API v2!"
    }
}