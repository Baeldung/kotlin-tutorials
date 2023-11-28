package com.baeldung.kotest.assertions.type

import io.kotest.assertions.shouldFail
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.matchers.types.shouldBeTypeOf
import io.kotest.matchers.types.shouldNotBeInstanceOf
import io.kotest.matchers.types.shouldNotBeTypeOf

class VehicleUnitTest : FunSpec({

    test("Car should be instance of Vehicle") {
        val car = Car(10)

        car.shouldBeInstanceOf<Vehicle>()
    }

    test("Car should be instance of Car") {
        val car = Car(10)

        car.shouldBeInstanceOf<Car>()
    }

    test("Motorcycle should not be instance of Car") {
        val vehicle: Vehicle = Motorcycle(15)

        vehicle.shouldNotBeInstanceOf<Car>()
    }

    test("Motorcycle should not be instance of Vehicle") {
        val vehicle: Vehicle = Motorcycle(15)

        shouldFail {
            vehicle.shouldNotBeInstanceOf<Vehicle>()
        }
    }

    test("A Car should be of type Car") {
        val vehicle: Vehicle = Car(10)

        vehicle.shouldBeTypeOf<Car>()
        shouldFail {
            vehicle.shouldBeTypeOf<Vehicle>()
        }
    }

    test("A Motorcycle should not be of type Car") {
        val vehicle: Vehicle = Motorcycle(15)

        vehicle.shouldNotBeTypeOf<Car>()
    }

    test("A Motorcycle should not be of type Vehicle") {
        val motorcycle: Vehicle = Motorcycle(15)

        motorcycle.shouldNotBeTypeOf<Vehicle>()
    }
})
