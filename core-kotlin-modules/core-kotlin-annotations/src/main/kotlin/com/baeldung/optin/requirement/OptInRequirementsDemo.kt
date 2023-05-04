package com.baeldung.optin.requirement


@RequiresOptIn
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class MyClass

@RequiresOptIn(level = RequiresOptIn.Level.WARNING, message = "This is an experimental API. It may be changed or removed in the future.")
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class ExperimentalClassApi


class OptInRequirementsDemo {

    @ExperimentalClassApi
    private fun someExperimentalApi(): Unit {
    }

    @ExperimentalClassApi
    private fun anotherExperimentalApi() {
        someExperimentalApi()
    }

    @OptIn(ExperimentalClassApi::class)
    var i = someExperimentalApi()
}
