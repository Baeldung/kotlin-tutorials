package com.baeldung.koin.dagger.migration

import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module


val appModule = module {
    single { MyRepository() }
    factory { MyService(get()) }
}
//
//fun main() {
//    startKoin {
//        modules(appModule)
//    }
//    val myApplication = MyApplication()
//    myApplication.run()
//}

fun main() {
    val myApplication = MyApplicationDagger()

    myApplication.run()
}