package com.baeldung.di.kodein


interface UserComponent {
    fun inject(app: KodeinUnitTest)
}