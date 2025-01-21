package com.baeldung.formatting

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.checkAll
import org.assertj.core.api.Assertions.assertThat

class NumberFormatUnitTest : ShouldSpec({

    listOf( // different implementations of the formatting feature
        "FormatByChunking" to { it: Int -> FormatByChunking.formatted(it) },
        "FormatByStringFormat" to { it: Int -> FormatByStringFormat.formatted(it) },
        "FormatByDecimalFormatGermany" to { it: Int -> FormatByDecimalFormatGermany.formatted(it) },
        "FormatByDecimalFormat" to { it: Int -> FormatByDecimalFormat.formatted(it) }
    ).forEach { (type, function) ->

        // Property based test (for each implementation)
        should("return correctly formatted string with $type") {
            checkAll(Arb.positiveInt()) { number ->
                var result = function(number)

                assertThat(result).containsPattern("^(\\d{1,3}(\\.\\d{3})*|\\d+)$")
                assertThat(number.toString()).isEqualTo(result.replace(".", ""))
                if (number > 999) assertThat(result).contains(".")
                else assertThat(result).doesNotContain(".")
            }
        }

        listOf( // examples with given number and expected string
            100_000 to "100.000",
            1_234_567 to "1.234.567",
            0 to "0",
            12 to "12",
            456 to "456"
        ).forEach { (number, expected) ->

            // Parameterised; Example based test
            should("return expected string '$expected' for $number with $type") {
                assertThat(function(number)).isEqualTo(expected)
            }
        }

    }
})
