package com.baeldung.staticinit

class Static {

    companion object {
        var answer: String

        init {
            answer = "42"
            println("Initialized")
        }
    }
}