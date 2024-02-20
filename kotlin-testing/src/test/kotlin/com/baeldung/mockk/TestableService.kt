package com.baeldung.mockk

class TestableService {
    fun getDataFromDb(testParameter: String): String {
        println("Test Parameter is: $testParameter")
        // query database and return matching value
        return "Value from DB"
    }

    fun doSomethingElse(testParameter: String): String {
        println("Test Parameter is: $testParameter")
        return "I don't want to!"
    }

    fun addHelloWorld(strList: MutableList<String>) {
        println("addHelloWorld() is called")
        strList += "Hello World!"
    }
}