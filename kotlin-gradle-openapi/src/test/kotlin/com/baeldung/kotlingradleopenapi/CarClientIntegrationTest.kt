package com.baeldung.kotlingradleopenapi

import com.baeldung.car.client.api.CarsApi
import com.baeldung.kotlingradleopenapi.service.CarService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.web.server.LocalServerPort
import com.baeldung.car.client.model.CarBody as ClientCarBody

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CarClientIntegrationTest {
    @LocalServerPort
    var serverPort: Int = 0

    @Autowired
    lateinit var carService: CarService

    @Test
    fun given_spec_client_when_client_method_is_called_then_an_entity_is_created() {
        val car = CarsApi("http://localhost:$serverPort")
          .createCar(ClientCarBody(model = "CM-X", make = "Gokyoro", year = 2021))
        assertNotNull(car)
        val entityCar = carService.getCar(car.id)
        assertNotNull(entityCar)
        assertEquals(car.make, entityCar?.make)
        assertEquals(car.model, entityCar?.model)
        assertEquals(car.year, entityCar?.year?.value)
    }
}