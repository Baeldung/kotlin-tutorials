package com.baeldung.mapvsflatmapvsflatten

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MapVsFlatMapVsFlattenUnitTest {

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
            Order(listOf(OrderLine("Tomato", 3), OrderLine("Garlic", 4))),
            Order(listOf(OrderLine("Potato", 5), OrderLine("Chives", 6))),
        )

        val lines: List<OrderLine> = orders.flatMap { it.lines }
        val names = lines.map { it.name }.distinct()
        assertThat(names).containsExactlyInAnyOrder("Garlic", "Chives", "Tomato", "Potato")
    }

    @Test
    fun `flatten should flatten the nested collections without transformation`() {
        val orderLines = listOf(
            listOf(OrderLine("Garlic", 1), OrderLine("Chives", 2)),
            listOf(OrderLine("Tomato", 3), OrderLine("Garlic", 4)),
            listOf(OrderLine("Potato", 5), OrderLine("Chives", 6)),
        )

        val lines: List<OrderLine> = orderLines.flatten()
        val expected = listOf(
            OrderLine("Garlic", 1),
            OrderLine("Chives", 2),
            OrderLine("Tomato", 3),
            OrderLine("Garlic", 4),
            OrderLine("Potato", 5),
            OrderLine("Chives", 6),
        )
        assertThat(lines).hasSize(6).isEqualTo(expected)
    }

    @Test
    fun `flatMap should get same result of map and then flatten`() {
        val orders = listOf(
            Order(listOf(OrderLine("Garlic", 1), OrderLine("Chives", 2))),
            Order(listOf(OrderLine("Tomato", 3), OrderLine("Garlic", 4))),
            Order(listOf(OrderLine("Potato", 5), OrderLine("Chives", 6))),
        )

        val expected = listOf(
            OrderLine("Garlic", 1),
            OrderLine("Chives", 2),
            OrderLine("Tomato", 3),
            OrderLine("Garlic", 4),
            OrderLine("Potato", 5),
            OrderLine("Chives", 6),
        )

        val resultMapAndFlatten: List<OrderLine> = orders.map { it.lines }.flatten()
        val resultFlatMap:List<OrderLine> = orders.flatMap { it.lines }

        assertThat(resultFlatMap).isEqualTo(resultMapAndFlatten).hasSize(6).isEqualTo(expected)
    }
}

data class Order(val lines: List<OrderLine>)
data class OrderLine(val name: String, val price: Int)