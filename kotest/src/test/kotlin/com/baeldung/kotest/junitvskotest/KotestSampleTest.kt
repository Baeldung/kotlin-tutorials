package com.baeldung.kotest.junitvskotest

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.ints.shouldNotBeLessThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class KotestSampleTest : FunSpec({
    beforeSpec {
        println("Setting up test class")
    }

    afterSpec {
        println("Tearing down test class")
    }

    beforeEach {
        println("Setting up test")
    }

    afterEach {
        println("Tearing down test")
    }

    // This is a test
    test("Test Example") {
        val result = 2 + 2

        result shouldBe 4
        result shouldBeGreaterThan 0
        result shouldNotBeLessThan 0
        result shouldNotBe null

        println("Executing Test Example")
    }
})