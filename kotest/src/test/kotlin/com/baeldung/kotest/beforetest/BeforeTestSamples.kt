package com.baeldung.kotest.beforetest

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class BeforeTestSamples : FunSpec({

    val database = Database()

    beforeEach {
        database.addUser(User(1, "Admin"))
    }

    isolationMode = IsolationMode.InstancePerTest


    test("Accessing all users should include added user and Admin") {
        database.addUser(User(2, "SimpleUser"))

        database.all() shouldBe listOf(
            User(1, "Admin"),
            User(2, "SimpleUser")
        )
    }
})
