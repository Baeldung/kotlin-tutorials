package com.baeldung.crud.versioning.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ContentNegotiationGreetingController {

    @GetMapping("/greeting", produces = ["application/vnd.api.v1+json"])
    fun greetingV1(): Map<String, String> {
        return mapOf("message" to "Hello, welcome to the API v1!")
    }

    @GetMapping("/greeting", produces = ["application/vnd.api.v2+json"])
    fun greetingV2(): Map<String, String> {
        val messageMap = HashMap<String, String>()
        messageMap["message"] = "Hello, welcome to the API v2!"
        messageMap["additionalInfo"] = "This is additional information for API v2"
        return messageMap
    }
}