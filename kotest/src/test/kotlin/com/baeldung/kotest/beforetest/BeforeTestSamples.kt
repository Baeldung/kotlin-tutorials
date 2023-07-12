package com.baeldung.kotest.beforetest

import io.kotest.core.listeners.BeforeInvocationListener
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.test.TestCase
import io.kotest.matchers.shouldBe

class BeforeTestSamples : FunSpec({

    val userRepository = UserRepository()

    beforeEach {
        userRepository.addUser(User(1, "Admin"))
    }

    isolationMode = IsolationMode.InstancePerTest


    test("Accessing all users should include added user and Admin") {
        userRepository.addUser(User(2, "SimpleUser"))

        userRepository.all() shouldBe listOf(
            User(1, "Admin"),
            User(2, "SimpleUser")
        )
    }
})

class BeforeInvocationSamples : FunSpec({

    val userRepository = UserRepository()

    isolationMode = IsolationMode.InstancePerTest

    extension(object : BeforeInvocationListener {
        override suspend fun beforeInvocation(testCase: TestCase, iteration: Int) {
            userRepository.addUser(User(iteration.toLong(), "Admin"))
        }
    })


    test("Accessing all users should include added user and Admin").config(invocations = 30) {
        userRepository.addUser(User(2, "SimpleUser"))

        userRepository.all().last() shouldBe User(2, "SimpleUser")
    }
})
