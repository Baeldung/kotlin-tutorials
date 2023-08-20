package com.baeldung.kotest.datadriven

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.IsStableType
import io.kotest.datatest.WithDataTestName
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class PythagoreanTripleTest : FunSpec({
    context("Pythagorean triples tests") {
        withData(
            PythagTriple(3, 4, 5),
            PythagTriple(6, 8, 10),
            PythagTriple(8, 15, 17),
            PythagTriple(7, 24, 25)
        ) { (a, b, c) ->
            isPythagTriple(a, b, c) shouldBe true
        }
    }

    context("each service should support all HTTP methods") {
        val services = listOf("http://internal.foo", "http://internal.bar", "http://public.baz")
        val methods = listOf("GET", "POST", "PUT")
        withData(services) { service ->
            withData(methods) { method ->
                println("$service $method") // test service against method
            }
        }
    }

    context("Pythagorean triples tests with map") {
        withData(
            mapOf(
                "3, 4, 5" to PythagTriple(3, 4, 5),
                "6, 8, 10" to PythagTriple(6, 8, 10),
                "8, 15, 17" to PythagTriple(8, 15, 17),
                "7, 24, 25" to PythagTriple(7, 24, 25)
            )
        ) { (a, b, c) ->
            isPythagTriple(a, b, c) shouldBe true
        }
    }

    context("Pythagorean triples tests with name function") {
        withData(
            nameFn = { "${it.a}__${it.b}__${it.c}" },
            PythagTriple(3, 4, 5),
            PythagTriple(6, 8, 10),
            PythagTriple(8, 15, 17),
            PythagTriple(7, 24, 25)
        ) { (a, b, c) ->
            isPythagTriple(a, b, c) shouldBe true
        }
    }

})

//@IsStableType
data class PythagTriple(val a: Int, val b: Int, val c: Int) : WithDataTestName {
    override fun dataTestName() = "Pythagorean Triple: $a, $b, $c"
}
