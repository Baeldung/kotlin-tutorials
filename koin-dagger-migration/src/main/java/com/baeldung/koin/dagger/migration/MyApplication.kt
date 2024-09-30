package com.baeldung.koin.dagger.migration

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class MyApplication : KoinComponent {

    private val myService: MyService by inject()

    fun run() {
        val result = myService.performAction()
        println(result)
    }
}