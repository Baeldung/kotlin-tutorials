package com.baeldung.csv.kotlincsv

import com.baeldung.csv.model.TaxableGood
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.InputStream
import java.math.BigDecimal

fun readRelaxedCsv(inputStream: InputStream): List<TaxableGood> = csvReader {
    skipEmptyLine = true
    escapeChar = '\\'
}.open(inputStream) {
    readAllWithHeaderAsSequence().map {
        TaxableGood(
            it["Index"]!!.trim().toInt(),
            it.k("Item")!!.trim(),
            BigDecimal(it.k("Cost")),
            BigDecimal(it.k("Tax")),
            BigDecimal(it.k("Total"))
        )
    }.toList()
}

fun readStrictCsv(inputStream: InputStream): List<TaxableGood> = csvReader().open(inputStream) {
    readAllWithHeaderAsSequence().map {
        TaxableGood(
            it["Index"]!!.trim().toInt(),
            it["Item"]!!.trim(),
            BigDecimal(it["Cost"]),
            BigDecimal(it["Tax"]),
            BigDecimal(it["Total"])
        )
    }.toList()
}

internal fun Map<String, String>.k(key: String) = this[keys.find { it.contains(key) }]?.trim()