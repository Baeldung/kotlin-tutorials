package com.baeldung.customException

class CustomException {
    /**
     * A quick and simple custom exception.
     */
    class SimpleCustomException(message: String) : Exception(message)

    /**
     * A full-featured custom exception.
     */
    class MyCustomException : Exception {
        constructor() : super()
        constructor(message: String) : super(message)
        constructor(message: String, cause: Throwable) : super(message, cause)
        constructor(cause: Throwable) : super(cause)
    }

    /**
     * A full-featured custom exception with less boilerplate.
     */
    class CustomException(message: String? = null, cause: Throwable? = null) : Exception(message, cause)

    private fun throwMyCustomException() {
        throw MyCustomException("This is a detail message for my custom exception")
    }

    private fun catchMyCustomException() {
        try { // throw the exception
            throw MyCustomException("This is a detail message for the custom exception")
        } catch (e: MyCustomException) {
            // handle the exception here
        }
    }

}