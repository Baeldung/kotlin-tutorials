package com.baeldung.decorator

class Tinsel(tree: ChristmasTree) : TreeDecorator(tree) {

    override fun decorate(): String {
        return super.decorate() + decorateWithTinsel()
    }

    private fun decorateWithTinsel(): String {
        return " with Tinsel"
    }
}