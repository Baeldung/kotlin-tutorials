package com.baeldung.csv.apache

import com.baeldung.csv.model.TaxableGood
import org.apache.commons.csv.CSVFormat
import java.io.InputStream
import java.io.Writer
import java.math.BigDecimal

fun readCsv(inputStream: InputStream): List<TaxableGood> =
    CSVFormat.Builder.create(CSVFormat.DEFAULT).apply {
        setIgnoreSurroundingSpaces(true)
    }.build().parse(inputStream.reader())
        .drop(1) // Dropping the header
        .map {
            TaxableGood(
                index = it[0].toInt(),
                item = it[1],
                cost = BigDecimal(it[2]),
                tax = BigDecimal(it[3]),
                total = BigDecimal(it[4])
            )
        }

fun Writer.writeCsv(goods: List<TaxableGood>) {
    CSVFormat.DEFAULT.print(this).apply {
        printRecord("Index", "Item", "Cost", "Tax", "Total")
        goods.forEach { (index, item, cost, tax, total) -> printRecord(index, item, cost, tax, total) }
    }
}
