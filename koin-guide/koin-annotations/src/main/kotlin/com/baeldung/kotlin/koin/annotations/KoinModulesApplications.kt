package com.baeldung.kotlin.koin.annotations

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.context.startKoin
import org.koin.ksp.generated.defaultModule
import org.koin.ksp.generated.module
import kotlin.random.Random

@Single
class NumberService {

    fun generateRandomNumber(): Int = Random.nextInt()
}

object KoinDefaultModuleApplication {

    @JvmStatic
    fun main(args: Array<String>) {
        val koinApp = startKoin {
            printLogger()
            modules(defaultModule)
        }

        require(koinApp.koin.getAll<NumberService>().isNotEmpty())
    }
}

interface Database
class DatabaseImpl : Database

interface Repository
class RepositoryImpl(database: Database) : Repository

@Module
class DaoModule {

    @Single
    fun provideDatabase(): Database {
        return DatabaseImpl()
    }

    @Factory
    fun provideRepository(database: Database): Repository {
        return RepositoryImpl(database)
    }
}

object KoinAnnotationsApplication {

    @JvmStatic
    fun main(args: Array<String>) {
        val koinApp = startKoin {
            printLogger()
            modules(DaoModule().module)
        }

        require(koinApp.koin.getAll<Database>().isNotEmpty())
        require(koinApp.koin.getAll<Repository>().isNotEmpty())
    }
}

class ModuleAComponent

@Module
class ModuleA {

    @Single
    fun provideModuleAComponent(): ModuleAComponent {
        return ModuleAComponent()
    }
}

@Module(includes = [ModuleA::class])
class ModuleB

object KoinModulesCompositionApplication {

    @JvmStatic
    fun main(args: Array<String>) {
        val koinApp = startKoin {
            printLogger()
            modules(ModuleB().module)
        }

        require(koinApp.koin.getAll<ModuleAComponent>().isNotEmpty())
    }
}

@Module
@ComponentScan("com.baeldung.kotlin.koin.annotations.domain")
class DomainModule

object KoinScannedComponentsModulesApplication {

    @OptIn(KoinInternalApi::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val koinApp = startKoin {
            printLogger()
            modules(DomainModule().module)
        }

        require(koinApp.koin.instanceRegistry.instances.size == 2)
    }
}