package com.baeldung.autowired

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/inventory")
class InventoryController(val inventoryService: InventoryService) {
    @GetMapping
    fun inventory(): List<Vehicle> {
        return inventoryService.inventory()
    }
}

@Service
class InventoryService @Autowired constructor(
    val vehicleDao: VehicleDao
) {
    fun inventory(): List<Vehicle> {
        return listOf(
            Vehicle(2022, "Honda", "CRV", "LE", Dealer(1001, "ABC Automotive")),
            Vehicle(2022, "Toyota", "Camry", "XLE", Dealer(1002, "XYZ Dealership"))
        )
    }

    @Autowired
    lateinit var dealerDao: DealerDao
}

@Component
class DealerDao {
    @set: Autowired
    lateinit var reviews: DealerReviewsDao
}

@Component
class DealerReviewsDao {

}

@Component
class VehicleDao {
    lateinit var vehicleValueFinder: VehicleValueFinder
    lateinit var vehicleReviewDao: VehicleReviewDao

    @Autowired
    fun initialize(vehicleValueFinder: VehicleValueFinder,
                    vehicleReviewDao: VehicleReviewDao) {
        this.vehicleValueFinder = vehicleValueFinder
        this.vehicleReviewDao = vehicleReviewDao
    }
}

@Component
class VehicleReviewDao {

}

@Component
class VehicleValueFinder {

}

data class Vehicle(
    val year: Int,
    val make: String,
    val model: String,
    val trim: String,
    val dealer: Dealer
)

data class Dealer(
    val dealerId: Int,
    val dealerName: String
)
