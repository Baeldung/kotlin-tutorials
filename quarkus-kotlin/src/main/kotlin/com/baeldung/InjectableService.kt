package com.baeldung

import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class InjectableService {
    fun greeting() = "Hello, World!"
}