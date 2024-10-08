package com.baeldung.quarkus.kotlin

import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class InjectableService {
    fun greeting() = "Hello, World!"
}