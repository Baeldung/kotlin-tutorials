package com.baeldung.maps

import java.util.*

data class IceCreamShipment(
    val flavor: String,
    val quantity: Int,
    val quantityUnit: ShipmentUnit = ShipmentUnit.LBS,
    val timeReceived: Date = Date(),
    val isMelted: Boolean = false
)

enum class ShipmentUnit {
    LBS,
    KGS,
    BOXES
}
