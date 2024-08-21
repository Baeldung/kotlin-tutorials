package com.baeldung.kotlin.koin.annotations

import org.koin.core.annotation.Module
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Single
import org.koin.core.context.startKoin
import org.koin.core.error.NoDefinitionFoundException
import org.koin.core.error.ScopeNotCreatedException
import org.koin.core.parameter.ParametersHolder
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.getKoin
import org.koin.ksp.generated.defaultModule
import org.koin.ksp.generated.module

class ShoppingCart {

    private val items = mutableListOf<Int>()

    fun addItem(itemId: Int) {
        items.add(itemId)
    }

    fun getItemIds(): List<Int> = items

    fun clearCart() {
        items.clear()
    }
}

class Checkout(private val shoppingCart: ShoppingCart) {

    private var paid = false

    fun confirm() {
        // Business logic with shopping cart
        paid = true
        println("Successfully paid for items")
    }
}

class ShoppingService {

    private val koin = getKoin()

    fun startShopping(userId: String) {
        koin.createScope<ShoppingSessionScope>(scopeId = "shopping-$userId")
    }

    fun addItem(userId: String, itemId: Int) {
        val shoppingScope = koin.getScope(scopeId = "shopping-$userId")
        val cart = shoppingScope.get<ShoppingCart>()

        // Add items to the cart during the session
        cart.addItem(itemId)

        // Business logic with the cart
        println("Items in cart: ${cart.getItemIds()}")
    }

    fun checkoutAndEndShopping(userId: String) {
        // Clean up the scope when shopping ends
        val shoppingScope = koin.getScope(scopeId = "shopping-$userId")
        val cart = shoppingScope.get<ShoppingCart>()

        val checkoutScope = koin.createScope<CheckoutSessionScope>(
            scopeId = "checkout-$userId",
            // Register a scope parameter
            parametersOf(cart)
        )

        val checkout = checkoutScope.get<Checkout> {
            parametersOf(
                // Access scope's parameters
                checkoutScope.getSource<ParametersHolder>()!!.get<ShoppingCart>()
            )
        }

        checkout.confirm()
        cart.clearCart()

        shoppingScope.close()
        checkoutScope.close()
    }
}

class ShoppingSessionScope
class CheckoutSessionScope

@Module
class ShoppingModule {

    @Single
    fun provideShoppingService() = ShoppingService()

    @Scope(ShoppingSessionScope::class)
    fun provideShoppingCart() = ShoppingCart()

    @Scope(CheckoutSessionScope::class)
    fun provideCheckout(shoppingCart: ShoppingCart) = Checkout(shoppingCart)
}

object KoinScopesApplication {

    @JvmStatic
    fun main(args: Array<String>) {

        val koinApp = startKoin {
            printLogger()
            modules(
                ShoppingModule().module,
                defaultModule
            )
        }

        // Shopping cart is scoped, no global bean exists
        try {
            koinApp.koin.get<ShoppingCart>()
        } catch (e: Exception) {
            require(e is NoDefinitionFoundException)
        }

        val service = koinApp.koin.get<ShoppingService>()

        val shoppingUserId = "platform-user"
        // Shopping scope is not yet initialized for user
        try {
            koinApp.koin.getScope(scopeId = "shopping-$shoppingUserId")
        } catch (e: Exception) {
            require(e is ScopeNotCreatedException)
        }

        // Shopping scope is created with empty cart
        service.startShopping(shoppingUserId)

        val shoppingUserSession = koinApp.koin.getScope(scopeId = "shopping-$shoppingUserId")
        val shoppingUserCart = shoppingUserSession.get<ShoppingCart>()

        require(shoppingUserCart.getItemIds().isEmpty())

        // User adds an item to its shopping cart
        val addedItemId = 1
        service.addItem(shoppingUserId, addedItemId)

        require(shoppingUserCart.getItemIds().contains(addedItemId))

        // Shopping scope is ended and removed from memory
        service.checkoutAndEndShopping(shoppingUserId)

        try {
            koinApp.koin.getScope(scopeId = "shopping-$shoppingUserId")
        } catch (e: Exception) {
            require(e is ScopeNotCreatedException)
        }
    }
}