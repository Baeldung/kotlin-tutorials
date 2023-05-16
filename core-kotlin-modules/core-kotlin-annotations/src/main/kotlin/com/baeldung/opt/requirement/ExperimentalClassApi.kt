package com.baeldung.opt.requirement

@RequiresOptIn(level = RequiresOptIn.Level.WARNING, message = "This is an experimental API. It may be changed or removed in the future.")
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class ExperimentalClassApi {

}