package com.baeldung.constants

// This won't compile since SimpleClass is not a primitive or String
// const val constantAtTopLevel = SimpleClass()
const val CONSTANT_AT_TOP_LEVEL = "constant at top level"

class ConstantsBestPractices {
    companion object {
        const val CONSTANT_IN_COMPANION_OBJECT = "constant at in companion object"
    }
}