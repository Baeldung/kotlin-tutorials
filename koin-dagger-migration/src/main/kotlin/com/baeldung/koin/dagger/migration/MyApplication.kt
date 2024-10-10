package com.baeldung.koin.dagger.migration

import jakarta.inject.Inject
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class MyApplication : KoinComponent {

    private val myService: MyService by inject()

    fun run() {
        val result = myService.performAction()
        println(result)
    }
}

class MyApplicationDagger {
    @Inject
    lateinit var myService: MyServiceDagger

    init {
        DaggerAppComponent.create().inject(this)
    }

    fun run() {
        val result = myService.performAction()
        println (result)
    }
}