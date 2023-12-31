package com.baeldung.mediator

// Step 1: Introduce Mediator Interface
interface Mediator {
    fun pressButton()
}
// Light module
class Light(private val mediator: Mediator) {
    private var isOn: Boolean = false
    fun turnOn() {
        println("Light turned on")
        isOn = true
        mediator.pressButton()
    }
    fun turnOff() {
        println("Light turned off")
        isOn = false
        mediator.pressButton()
    }

    fun isOn(): Boolean {
        return isOn
    }
}
class Button(private val mediator: Mediator) {
    fun press() {
        mediator.pressButton()
    }
}
// Concrete Mediator
class ConcreteMediator : Mediator {
    private val light: Light = Light(this)
    private val button: Button = Button(this)
    override fun pressButton() {
        if (light.isOn()) {
            light.turnOff()
        } else {
            light.turnOn()
        }
    }
}
