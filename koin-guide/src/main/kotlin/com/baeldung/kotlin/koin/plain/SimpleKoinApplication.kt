package com.baeldung.kotlin.koin.plain

import org.koin.core.Koin
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.PrintLogger
import org.koin.core.module.dsl.singleOf
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.environmentProperties
import org.koin.fileProperties

object RuntimeWithStandaloneKoin {
    @JvmStatic
    fun main(args: Array<String>) {
        val app = koinApplication {
            modules(koinModule)
        }
        StandaloneKoinApplication(app.koin).invoke().let { println(it) }
    }
}

object RuntimeWithGlobalKoin {
    @JvmStatic
    fun main(args: Array<String>) {
        startKoin {
            logger(PrintLogger(Level.INFO))
            modules(compositeModule)
            fileProperties()
            properties(mapOf("a" to "b", "c" to "d"))
            environmentProperties()
            createEagerInstances()
        }
        val application = SimpleKoinApplication()
        val secondComponent = SecondComponent()
        println(application.invoke())
        println(application.askForInside())
        println(application.closingCircle)
        println(application.askForRumour())
        println(secondComponent.askForInside())
    }
}

class SimpleKoinApplication : KoinComponent {
    private val service: HelloSayer by inject()
    private val rumourMonger: RumourTeller = get()
    val closingCircle: BackLoop = get()
    private val rumourSource: RumourSource = get { parametersOf("Jack is kissing Alex") }

    fun invoke() = service.sayHello()
    fun askForInside() = rumourMonger.tellRumour()
    fun askForRumour() = rumourSource.tellRumour()
}

class SecondComponent : KoinComponent {
    private val rumourMonger: RumourSource = get { parametersOf("Max is kissing Alex") }
    fun askForInside() = rumourMonger.tellRumour()
}

class StandaloneKoinApplication(private val koinInstance: Koin) : KoinComponent {
    override fun getKoin(): Koin = koinInstance
    private val service: HelloSayer by inject()

    fun invoke() = service.sayHello()
}

class HelloSayer {
    fun sayHello() = "Hello!"
}

interface RumourTeller {
    fun tellRumour(): String
}

class RumourMonger(private val rumorSource: RumourSource) : RumourTeller {
    override fun tellRumour() = "One of my friend says: ${rumorSource.tellRumour()}"
}

class RumourSource(private val rumour: String = "Jane is seeing Gill") : RumourTeller {
    override fun tellRumour(): String = "I've heard that $rumour"
}

class BackLoop(val dependency: Dependency)

class Dependency

val koinModule = module {
    single { HelloSayer() }
    single<RumourTeller> { RumourMonger(get { parametersOf("I've seen nothing") }) }
    singleOf(::Dependency)
}

val staticRumourModule = module {
    factory { RumourSource() }
}

val factoryScopeModule = module {
    factory { (rumour: String) -> RumourSource(rumour) }
    singleOf(::BackLoop)
}

val compositeModule = module {
    includes(koinModule, factoryScopeModule)
}

fun helloSayer() = HelloSayer()

val factoryFunctionModule = module {
    single { helloSayer() }
}

val singleWithParamModule = module {
    single { (rumour: String) -> RumourSource(rumour) }
}

val namedSources = module {
    single(named("Silent Bob")) { RumourSource("I've seen nothing") }
    single(named("Jay")) { RumourSource("Jack is kissing Alex") }
}

val initByProperty = module {
    single { RumourSource(getProperty("rumour", "Some default rumour")) }
}