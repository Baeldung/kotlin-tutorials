package com.baeldung.mapvsflatmap

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MapVsFlatMapUnitTest {

    @Test
    fun `map should convert each element to another element`() {
        val order = Order(
          listOf(OrderLine("Tomato", 2), OrderLine("Garlic", 3), OrderLine("Chives", 2))
        )

        val names = order.lines.map { it.name }
        val totalPrice = order.lines.map { it.price }.sum()

        assertThat(names).containsExactly("Tomato", "Garlic", "Chives")
        assertEquals(7, totalPrice)
    }

    @Test
    fun `flatMap should flatten the one-to-many relation as expected`() {
        val orders = listOf(
          Order(listOf(OrderLine("Garlic", 1), OrderLine("Chives", 2))),
          Order(listOf(OrderLine("Tomato", 1), OrderLine("Garlic", 2))),
          Order(listOf(OrderLine("Potato", 1), OrderLine("Chives", 2))),
        )

        val lines: List<OrderLine> = orders.flatMap { it.lines }
        val names = lines.map { it.name }.distinct()
        assertThat(names).containsExactlyInAnyOrder("Garlic", "Chives", "Tomato", "Potato")
    }
}

class Order(val lines: List<OrderLine>)
class OrderLine(val name: String, val price: Int)