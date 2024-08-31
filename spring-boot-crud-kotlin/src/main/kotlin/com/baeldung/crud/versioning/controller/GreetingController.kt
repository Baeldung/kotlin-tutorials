package com.baeldung.crud.versioning.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingController {

    @GetMapping("/greeting")
    fun greeting(): String {
        return "Hello, welcome to the API!"
    }
}