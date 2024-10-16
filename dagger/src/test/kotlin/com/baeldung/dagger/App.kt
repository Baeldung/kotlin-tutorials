package com.baeldung.dagger

import dagger.Component

@Component(modules = [TestAppModule::class])
interface TestAppComponent {
    fun networkClient(): NetworkClientMultipleInstances
}