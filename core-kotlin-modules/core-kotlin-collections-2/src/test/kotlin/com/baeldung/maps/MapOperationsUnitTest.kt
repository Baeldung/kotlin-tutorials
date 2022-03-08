package com.baeldung.maps

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MapOperationsUnitTest {

    private val defaultIceCreamInventory = mapOf(
      "Vanilla" to 24,
      "Chocolate" to 14,
      "Strawberry" to 9,
      "Rocky Road" to 7,
      "Maple Walnut" to 0
    )

    @Test
    fun `Given a list of IceCreamShipments, When there are multiple of each type, Then generate a map`() {

        val shipments = listOf(
          IceCreamShipment("Chocolate", 3),
          IceCreamShipment("Strawberry", 7),
          IceCreamShipment("Vanilla", 5),
          IceCreamShipment("Chocolate", 5),
          IceCreamShipment("Vanilla", 1),
          IceCreamShipment("Rocky Road", 10),
        )

        // Using Kotlin functional APIs
        val iceCreamInventory = shipments
          .groupBy({ it.flavor }, { it.quantity })
          .mapValues { it.value.sum() }

        // Using a for loop - not recommended
//        val iceCreamInventory = mutableMapOf<String, Int>()
//
//        for (shipment in shipments){
//            val currentQuantity = iceCreamInventory[shipment.flavor] ?: 0
//            iceCreamInventory[shipment.flavor] = currentQuantity + shipment.quantity
//        }

        assertEquals(8, iceCreamInventory["Chocolate"])
        assertEquals(7, iceCreamInventory["Strawberry"])
        assertEquals(6, iceCreamInventory["Vanilla"])
        assertEquals(10, iceCreamInventory["Rocky Road"])
    }

    @Test
    fun `Given a map, When the key exists, Then getter methods return the value`() {

        val map = defaultIceCreamInventory

        assertEquals(24, map.get("Vanilla"))
        assertEquals(24, map["Vanilla"])
        assertEquals(24, map.getValue("Vanilla"))
        assertEquals(24, map.getOrElse("Vanilla", { print("Warning: Flavor not found in map"); 0 }))
        assertEquals(24, map.getOrDefault("Vanilla", 0))
    }

    @Test
    fun `Given a map, When the key doesn't exist, Then getter methods return null or other default actions`() {

        val map = defaultIceCreamInventory

        assertNull(map.get("Banana"))
        assertNull(map["Banana"])
        assertThrows(NoSuchElementException::class.java) { map.getValue("Banana") }
        assertEquals(0, map.getOrElse("Banana", { print("Warning: Flavor not found in map"); 0 }))
        assertEquals(0, map.getOrDefault("Banana", 0))
    }

    @Test
    fun `Given a mutable map, When adding a value, Then it is added to the map`() {

        val iceCreamSales = mutableMapOf<String, Int>()

        iceCreamSales.put("Chocolate", 1)
        iceCreamSales["Vanilla"] = 2
        iceCreamSales.putAll(setOf("Strawberry" to 3, "Rocky Road" to 2))
        iceCreamSales += mapOf("Maple Walnut" to 1, "Mint Chocolate" to 4)

        assertEquals(1, iceCreamSales["Chocolate"])
        assertEquals(2, iceCreamSales["Vanilla"])
        assertEquals(3, iceCreamSales["Strawberry"])
        assertEquals(2, iceCreamSales["Rocky Road"])
        assertEquals(1, iceCreamSales["Maple Walnut"])
        assertEquals(4, iceCreamSales["Mint Chocolate"])

        iceCreamSales.put("Chocolate", 3)
        assertEquals(3, iceCreamSales["Chocolate"]) //overwrites the previous value
    }

    @Test
    fun `Given a mutable map, When updating a value that exists in the map, Then it updates correctly`() {

        val iceCreamSales = mutableMapOf("Chocolate" to 2)

        val saleAmt = 1

        iceCreamSales.compute("Chocolate") { _, currValue -> currValue?.plus(saleAmt) ?: saleAmt }
        iceCreamSales.merge("Chocolate", saleAmt, Int::plus)

        assertEquals(4, iceCreamSales["Chocolate"])
    }

    @Test
    fun `Given a mutable map, When removing a key that exists in the map, Then it is removed`() {

        val map = defaultIceCreamInventory.toMutableMap()

        map.remove("Strawberry")
        map -= "Chocolate"

        assertNull(map["Strawberry"])
        assertNull(map["Chocolate"])

        // Remove by matching key and value
        map.remove("Rocky Road", 7)
        assertNull(map["Rocky Road"])
    }

    @Test
    fun `Given a mutable map, When it has entries, Then clear will remove all entries`() {

        val map = defaultIceCreamInventory.toMutableMap()

        map.clear()
        assertEquals(0, map.size)
    }

    @Test
    fun `Given a map, When it has entries, Then filter will return a modified map with some entries removed`() {

        val inventory = defaultIceCreamInventory

        val lotsLeft = inventory.filterValues { qty -> qty > 10 }
        assertEquals(setOf("Vanilla", "Chocolate"), lotsLeft.keys)

        val result = inventory.filter { (flavor, amt) -> flavor != "Rocky Road" && amt < 10 }
        assertEquals(setOf("Strawberry", "Maple Walnut"), result.keys)
    }

    @Test
    fun `Given a map, When it has entries, Then filterTo will add some entries to another map`() {

        val inventory = defaultIceCreamInventory
        val popularFlavors = mutableMapOf<String, Int>()

        inventory.filterTo(popularFlavors, { (flavor, _) -> flavor == "Chocolate" || flavor == "Vanilla"})

        assertEquals(setOf("Vanilla", "Chocolate"), popularFlavors.keys)
    }

    @Test
    fun `Given a map, When it has entries, Then map each entry to a string expression`() {

        val inventory = defaultIceCreamInventory

        val asStrings = inventory.map { (flavor, qty) -> "$qty tubs of $flavor" }

        assertTrue(asStrings.containsAll(setOf(
          "24 tubs of Vanilla",
          "14 tubs of Chocolate",
          "9 tubs of Strawberry",
        )))
        assertEquals(5, asStrings.size)
    }


    @Test
    fun `Given a map, When it has entries, Then keys returns every key in the map`() {

        val inventory = defaultIceCreamInventory

        val allFlavors = inventory.keys

        assertTrue(allFlavors.containsAll(setOf(
          "Vanilla",
          "Chocolate",
          "Strawberry",
          "Rocky Road",
          "Maple Walnut"
        )))
        assertEquals(5, allFlavors.size)
    }

    @Test
    fun `Given a map, When it has entries, Then values returns every value in the map`() {

        val inventory = defaultIceCreamInventory

        val allFlavors = inventory.values

        assertTrue(allFlavors.containsAll(setOf(24, 14, 9, 7, 0)))
        assertEquals(5, allFlavors.size)
    }

    @Test
    fun `Given a list of shipments and a map of sales, When merging with inventory, Then update the inventory`() {

        val inventory = mutableMapOf(
          "Vanilla" to 24,
          "Chocolate" to 14,
          "Strawberry" to 9,
        )

        val sales = mapOf("Vanilla" to 7, "Chocolate" to 4, "Strawberry" to 5)

        val shipments = mapOf("Chocolate" to 3, "Strawberry" to 7, "Rocky Road" to 5)

        with(inventory) {
            sales.forEach { merge(it.key, it.value, Int::minus) }
            shipments.forEach { merge(it.key, it.value, Int::plus) }
        }

        assertEquals(17, inventory["Vanilla"]) // 24 - 7 + 0
        assertEquals(13, inventory["Chocolate"]) // 14 - 4 + 3
        assertEquals(11, inventory["Strawberry"]) // 9 - 5 + 7
        assertEquals(5, inventory["Rocky Road"]) // 0 - 0 + 5
    }
}