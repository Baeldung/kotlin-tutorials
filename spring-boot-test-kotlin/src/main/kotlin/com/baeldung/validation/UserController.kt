package com.baeldung.validation

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class UserController {

    @PostMapping("/users")
    fun create(
        @Valid @RequestBody
        user: User,
    ): ResponseEntity<String> {
        // Code to create a new user
        return ResponseEntity.ok("User created successfully")
    }

    @PostMapping("/users/address")
    fun createUserWithAddress(
        @Valid @RequestBody
        user: UserAddress,
    ): ResponseEntity<String> {
        // Code to create a new user with address
        return ResponseEntity.ok("User with address created successfully")
    }
}
