package com.baeldung.kotest.beforetest

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class BeforeSpecSamples : FunSpec({

    val userRepository = UserRepository()

    beforeSpec {
        userRepository.addUser(User(1, "Admin"))
    }

    test("Accessing all users should include added user and Admin") {
        userRepository.addUser(User(2, "SimpleUser"))

        userRepository.all() shouldBe listOf(
            User(1, "Admin"),
            User(2, "SimpleUser")
        )
    }
})
