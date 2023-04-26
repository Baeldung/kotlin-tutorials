package com.baeldung.inline.classes

interface Drawable {
    fun draw()
}

@JvmInline
value class CircleRadius(private val circleRadius : Double) : Drawable {
    val diameterOfCircle get() = 2 * circleRadius
    fun areaOfCircle() = 3.14 * circleRadius * circleRadius

    init {
        require(circleRadius > 0) { "Circle radius must be positive" }
    }

    override fun draw() {
        println("Draw my circle")
    }
}