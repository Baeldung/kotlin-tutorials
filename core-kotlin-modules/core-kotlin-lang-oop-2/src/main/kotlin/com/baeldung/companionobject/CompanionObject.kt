package com.baeldung.companionobject

class CompanionObject {
    class ClassName {
        fun anotherFun() {
            println(propertyName)
        }

        companion object {
            @JvmStatic
            val propertyName: String = "Something..."
            fun funName() {
                //...
            }
        }
    }

    class MyClass {
        companion object Factory {
            fun createInstance(): MyClass = MyClass()
        }
    }

    abstract class AbstractFactory {
        interface Theme {
            fun someFunction(): String
        }

        abstract class FactoryCreator {
            abstract fun produce(): Theme
        }

        class FirstRelatedClass : Theme {
            companion object Factory : FactoryCreator() {
                override fun produce() = FirstRelatedClass()
            }

            override fun someFunction(): String {
                return "I am from the first factory."
            }
        }

        class SecondRelatedClass : Theme {
            companion object Factory : FactoryCreator() {
                override fun produce() = SecondRelatedClass()
            }

            override fun someFunction(): String {
                return "I am from the second factory."
            }
        }
    }

    interface MyInterface {
        companion object {
            const val PROPERTY = "value"
        }
    }
}

fun main() {
    val firstFactory: CompanionObject.AbstractFactory.FactoryCreator = CompanionObject.AbstractFactory.FirstRelatedClass
    println(firstFactory.produce().someFunction())

    val secondFactory: CompanionObject.AbstractFactory.FactoryCreator = CompanionObject.AbstractFactory.SecondRelatedClass
    println(secondFactory.produce().someFunction())
}