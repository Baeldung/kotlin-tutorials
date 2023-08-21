package com.baeldung.kotest.datadriven

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.IsStableType
import io.kotest.datatest.WithDataTestName
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class PythagoreanTripleTest : FunSpec({
    context("Pythagorean triples tests") {
        withData(
            DefaultPythagTriple(3, 4, 5),
            DefaultPythagTriple(6, 8, 10),
            DefaultPythagTriple(8, 15, 17),
            DefaultPythagTriple(7, 24, 25)
        ) { (a, b, c) ->
            isPythagTriple(a, b, c) shouldBe true
        }
    }

    context("StablePythagorean triples tests") {
        withData(
            StablePythagTriple(3, 4, 5),
            StablePythagTriple(6, 8, 10),
            StablePythagTriple(8, 15, 17),
            StablePythagTriple(7, 24, 25)
        ) { (a, b, c) ->
            isPythagTriple(a, b, c) shouldBe true
        }
    }

    context("NamedPythagorean triples tests") {
        withData(
            NamedPythagTriple(3, 4, 5),
            NamedPythagTriple(6, 8, 10),
            NamedPythagTriple(8, 15, 17),
            NamedPythagTriple(7, 24, 25)
        ) { (a, b, c) ->
            isPythagTriple(a, b, c) shouldBe true
        }
    }

    context("Pythagorean triples tests with map") {
        withData(
            mapOf(
                "3, 4, 5" to DefaultPythagTriple(3, 4, 5),
                "6, 8, 10" to DefaultPythagTriple(6, 8, 10),
                "8, 15, 17" to DefaultPythagTriple(8, 15, 17),
                "7, 24, 25" to DefaultPythagTriple(7, 24, 25)
            )
        ) { (a, b, c) ->
            isPythagTriple(a, b, c) shouldBe true
        }
    }

    context("Pythagorean triples tests with name function") {
        withData(
            nameFn = { "${it.a}__${it.b}__${it.c}" },
            DefaultPythagTriple(3, 4, 5),
            DefaultPythagTriple(6, 8, 10),
            DefaultPythagTriple(8, 15, 17),
            DefaultPythagTriple(7, 24, 25)
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

})

data class DefaultPythagTriple(val a: Int, val b: Int, val c: Int)

@IsStableType
data class StablePythagTriple(val a: Int, val b: Int, val c: Int)

data class NamedPythagTriple(val a: Int, val b: Int, val c: Int) : WithDataTestName {
    override fun dataTestName() = "Pythagorean Triple: $a, $b, $c"
}
