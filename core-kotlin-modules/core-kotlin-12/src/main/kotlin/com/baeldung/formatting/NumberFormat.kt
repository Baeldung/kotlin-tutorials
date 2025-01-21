package com.baeldung.formatting

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

/**
 * Interface for the different formatting implementation.
 */
interface NumberFormat {

    /**
     * Returns provided number as string with thousands separator.
     *
     * 1000 -> '1.000'
     * 750000 -> '750.000'
     */
    fun formatted(number: Int): String
}

object FormatByDecimalFormat : NumberFormat {
    override fun formatted(number: Int): String =
        DecimalFormat("#,###")
            .format(number)
            .replace(",", ".")
}

object FormatByDecimalFormatGermany : NumberFormat {
    override fun formatted(number: Int): String =
        DecimalFormat("#,###", DecimalFormatSymbols(Locale.GERMANY))
            .format(number)
}

object FormatByStringFormat : NumberFormat {
    override fun formatted(number: Int): String =
        String.format(Locale.GERMANY, "%,d", number)
}

object FormatByChunking : NumberFormat {
    override fun formatted(number: Int): String =
        number.toString()
            .reversed() // 15000 -> 00051
            .chunked(3) // [000] [51]
            .joinToString(".") // 000.51
            .reversed() // 15.000
}
