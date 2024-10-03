package com.baeldung.koin.dagger.migration

import dagger.Component

@Component(modules = [MyModule::class])
interface AppComponent {
    fun inject(app: MyApplicationDagger)
}