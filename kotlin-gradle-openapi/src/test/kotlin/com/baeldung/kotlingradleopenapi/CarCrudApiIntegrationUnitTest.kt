package com.baeldung.kotlingradleopenapi

import com.baeldung.kotlingradleopenapi.service.CarService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.Year


@SpringBootTest
class CarCrudServiceApplicationTests {

    @Autowired
    lateinit var carService: CarService

    @Test
    fun `creates a car`() {
        carService.createCar(model = "CM-X", make = "Gokyoro", year = Year.of(2021))
        val all = carService.getAllCars()
        assertEquals(1, all.size)
    }
}