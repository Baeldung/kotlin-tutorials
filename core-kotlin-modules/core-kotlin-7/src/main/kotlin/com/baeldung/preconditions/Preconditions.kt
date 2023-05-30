package com.baeldung.preconditions

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

fun printPositiveNumber(num: Int) {
    require(num > 0) { "Number must be positive" }
    println(num)
}

fun printListSize(list: List<Int>, size: Int) {
    check(list.size == size) { "List must contain $size elements" }
    println(list)
}

fun divide(a: Int, b: Int): Int {
    if (b == 0) {
        error("Cannot divide by zero")
    }
    return a / b
}

fun processJsonArrayOrObject(json: String): JsonNode {
    val genericJson: JsonNode = jacksonObjectMapper().readTree(json)

    when (genericJson) {
        is ArrayNode -> { /* we have a json list */ }
        is ObjectNode -> { /* we have an object */ }
        else -> error("This function only handles list and object wrappers in Json")
    }

    return genericJson
}
