package com.baeldung.kotlingradleopenapi

import com.baeldung.car.model.CarBody
import com.baeldung.kotlingradleopenapi.service.CarService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal
import java.time.Year


@SpringBootTest
@AutoConfigureMockMvc
class CarCrudServiceApplicationTests {

    @Autowired
    lateinit var carService: CarService

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    fun when_called_with_proper_data_then_creates_an_entity() {
        mockMvc.perform(
          post("/v1/cars/")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(carBodies[0]))
        )
          .andExpect(status().isOk)
          .andExpect(jsonPath("\$.model").value("CM-X"))
    }

    @Test
    fun when_called_on_existing_car_then_updates_price() {
        carBodies.forEach { (model, make, year) ->
            carService.createCar(model, make, Year.of(year))
        }
        mockMvc.perform(
          post("/v1/cars/price/{carId}", 0)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(BigDecimal("22000.43")))
        )
          .andExpect(status().isAccepted)
        val changedCar = carService.getCar(0)
        assertEquals(changedCar?.price, BigDecimal("22000.43"))
    }


    companion object {
        val carBodies = listOf(
          CarBody(model = "CM-X", make = "Gokyoro", year = 2021),
          CarBody(model = "Carus", make = "Gokyoro", year = 2021),
          CarBody(model = "Corona", make = "Gokyoro", year = 2021),
          CarBody(model = "Prima", make = "Hooligan", year = 2021),
        )
    }
}