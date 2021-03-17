package com.baeldung.argumentcaptor

class OrderService(
    private val orderRepository: OrderRepository,
    private val messageDispatcher: IMessageDispatcher,
) {

    fun createOrder(order: Order, confirmRequired: Boolean) {

        val orderId = orderRepository.createNewOrder(order)

        if (confirmRequired) {
            messageDispatcher.sendMessage("Order Created") { success ->
                if (success) {
                    orderRepository.updateOrderConfirmation(orderId, true);
                }
                else {
                    throw IllegalStateException("Failed to send confirmation message!")
                }}
        }
        else {
            messageDispatcher.sendMessage("Order Created") {}
        }
    }
}

interface OrderRepository {
    fun createNewOrder(order: Order): Int

    fun updateOrderConfirmation(orderId: Int, confirmed: Boolean)
}

data class Order(val food: String, val drink: String, val tableNumber: Int)