package com.baeldung.kotest

import io.kotest.core.spec.style.FeatureSpec

class HomePageTests : FeatureSpec({
    feature("signup") {
        scenario("should allow user to signup with email") {
            // test code
        }
    }
    feature("signin") {
        scenario("should allow user with valid credentials to login") {
            // test code
        }
    }
})