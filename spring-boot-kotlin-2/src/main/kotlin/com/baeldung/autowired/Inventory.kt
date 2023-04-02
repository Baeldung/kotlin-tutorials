package com.baeldung.autowired

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RestController

@RestController
class InventoryController(val inventoryService: InventoryService) {

}

@Service
class InventoryService @Autowired constructor(
    private val vehicleDao: VehicleDao
) {
    @Autowired
    private lateinit var dealerDao: DealerDao
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
    private lateinit var vehicleValueFinder: VehicleValueFinder
    private lateinit var vehicleReviewDao: VehicleReviewDao

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
