package com.baeldung.argumentcaptor

import com.nhaarman.mockito_kotlin.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class OrderServiceTest {

    private val orderRepository: OrderRepository = mock()
    private val messageDispatcher: MessageDispatcher = mock()

    private val orderService = OrderService(orderRepository, messageDispatcher)

    @Test
    fun `when message send succeeds and confirm required, then order is updated with successful confirm`() {

        val order = Order("pizza", "coke", 1)
        val orderId = 123
        whenever(orderRepository.createNewOrder(order)).thenReturn(orderId)

        orderService.createOrder(order, true)

        val captor = argumentCaptor<(Boolean) -> Unit>()
        verify(messageDispatcher).sendMessage(eq("Order Created"), captor.capture())

        captor.firstValue.invoke(true)
        verify(orderRepository).updateOrderConfirmation(orderId, true)
    }

    @Test
    fun `when message send fails and confirm required, then throw exception`() {

        val order = Order("pizza", "coke", 1)
        val orderId = 123

        whenever(orderRepository.createNewOrder(order)).thenReturn(orderId)

        orderService.createOrder(order, true)

        val captor = argumentCaptor<(Boolean) -> Unit>()
        verify(messageDispatcher).sendMessage(eq("Order Created"), captor.capture())

        assertThrows<IllegalStateException> { captor.firstValue.invoke(false) }
    }

    @Test
    fun `when message send succeeds and confirm not required, then order is created`() {

        val order = Order("pizza", "coke", 1)
        val orderId = 123

        whenever(orderRepository.createNewOrder(order)).thenReturn(orderId)

        orderService.createOrder(order, false)

        val captor = argumentCaptor<(Boolean) -> Unit>()
        verify(messageDispatcher).sendMessage(eq("Order Created"), captor.capture())
    }

    @Test
    fun `when message send fails and confirm not required, then order is created`() {

        val order = Order("pizza", "coke", 1)
        val orderId = 123

        whenever(orderRepository.createNewOrder(order)).thenReturn(orderId)

        orderService.createOrder(order, false)

        val captor = argumentCaptor<(Boolean) -> Unit>()
        verify(messageDispatcher).sendMessage(eq("Order Created"), captor.capture())
    }
}