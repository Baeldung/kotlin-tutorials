package com.baeldung.testresources

import io.kotest.core.spec.style.StringSpec

class KotestTestResourcesUnitTest : StringSpec({

    beforeSpec { println("Initializing Resources Before All Tests") }
    beforeTest { println("Initializing Resources Before Each Test") }
    afterTest { println("Cleaning Resources After Each Test") }
    afterSpec { println("Cleaning Resources After All Tests") }

    "test something" {
        println("Running Test")
    }
})