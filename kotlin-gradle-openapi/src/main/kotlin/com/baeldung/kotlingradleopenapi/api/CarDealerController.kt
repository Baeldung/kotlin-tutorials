package com.baeldung.kotlingradleopenapi.api

import com.baeldung.kotlingradleopenapi.service.CarService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import io.swagger.v3.oas.annotations.parameters.RequestBody as OASRequestBody // Need to do this because of https://github.com/swagger-api/swagger-core/issues/3628

@RestController
@RequestMapping("/v1/cars")
class CarDealerController(private val carService: CarService) {


    @Operation(summary = "Sets a price for a chosen car", description = "Returns 202 if successful")
    @ApiResponses(
      value = [
          ApiResponse(responseCode = "202", description = "Successful Operation"),
          ApiResponse(responseCode = "404", description = "Such a car does not exist"),

      ]
    )
    @PostMapping("/price/{carId}", consumes = ["application/json"])
    fun setPrice(
      @PathVariable carId: Int,
      @RequestBody @OASRequestBody(
        description = "New price for the car",
        content = [Content(
          mediaType = "application/json",
          schema = Schema(type = "number", format = "float", minimum = "0.0", example = "23000.34"),
        )]
      ) price: BigDecimal
    ): ResponseEntity<Unit> {
        carService.setPrice(carId, price)
        return ResponseEntity.accepted().build()
    }

    @Operation(summary = "Get a price of a car", description = "Returns a car price")
    @ApiResponses(
      value = [
          ApiResponse(responseCode = "200", description = "Successful Operation"),
          ApiResponse(responseCode = "404", description = "Such a car does not exist"),

      ]
    )
    @GetMapping("/price/{carId}")
    fun getCarPrice(@PathVariable carId: Int): ResponseEntity<BigDecimal> =
      carService.getCar(carId)?.price?.let { ok(it) }
        ?: throw IllegalArgumentException("Car not found")

    @Operation(summary = "Get a dealer view of the car collection", description = "Returns all cars with their price")
    @ApiResponses(
      value = [ApiResponse(
        responseCode = "200",
        description = "Successful Operation",
        content = [Content(mediaType = "application/json", schema = Schema(implementation = DealerCar::class))]
      )]
    )
    @GetMapping("/price/all")
    fun getDealerView(): ResponseEntity<List<DealerCar>> =
      carService.getAllCars().map { DealerCar(it.model, it.make, it.year.value, it.price) }
        .let { ok(it) }
}

@Schema(description = "Model for a dealer's view of a car.")
data class DealerCar(
  @field:Schema(description = "Car model", example = "Focus", type = "string")
  val model: String,
  @field:Schema(description = "Car maker corporation", example = "Ford", type = "string")
  val make: String,
  @field:Schema(
    description = "A year when this car was made",
    example = "2021",
    type = "int",
    minimum = "1900",
    maximum = "2500"
  )
  val year: Int,
  @field:Schema(description = "Car price", type = "number", format = "float", minimum = "0.0", example = "23000.34")
  val price: BigDecimal
)