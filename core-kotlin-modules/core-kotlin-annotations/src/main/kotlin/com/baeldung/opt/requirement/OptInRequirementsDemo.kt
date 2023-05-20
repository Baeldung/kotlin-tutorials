@file:OptIn(ExperimentalClassApi::class)

package com.baeldung.opt.requirement

class OptInRequirementsDemo {

    @ExperimentalClassApi
    fun someExperimentalApi(): Unit {
    }

    @ExperimentalClassApi
    fun anotherExperimentalApi() {
        someExperimentalApi()
    }

    @OptIn(ExperimentalClassApi::class)
    fun OptInOnEntireFunction(){
        someExperimentalApi()
    }

    @OptIn(ExperimentalClassApi::class)
    var i = someExperimentalApi()
}