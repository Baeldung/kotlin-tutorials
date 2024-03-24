package com.baeldung.kotlingradleopenapi.service

import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.Year
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger


@Component
class CarService {
    private val cars: ConcurrentHashMap<Int, Car> = ConcurrentHashMap()
    private val size = AtomicInteger(0)

    fun createCar(model: String, make: String, year: Year): Car =
      Car(size.getAndIncrement(), model, make, year, -BigDecimal.ONE).also {
          cars[it.id] = it
      }

    fun getCar(id: Int): Car? = cars[id]

    fun getAllCars(): List<Car> = cars.values.toList()

    fun setPrice(carId: Int, price: BigDecimal) {
        cars[carId] = cars[carId]?.copy(price = price) ?: throw IllegalArgumentException("Not found")
    }
}