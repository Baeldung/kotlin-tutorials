package com.baeldung.csv.jackson

import com.baeldung.csv.model.TaxableGood
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvParser
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import java.io.InputStream
import java.io.OutputStream

val csvMapper = CsvMapper().apply {
    enable(CsvParser.Feature.TRIM_SPACES)
    enable(CsvParser.Feature.SKIP_EMPTY_LINES)
}

val schema = CsvSchema.builder()
    .addNumberColumn("Index")
    .addColumn("Item")
    .addColumn("Cost")
    .addColumn("Tax")
    .addColumn("Total")
    .build()

fun readCsv(inputStream: InputStream): List<TaxableGood> =
    csvMapper.readerFor(TaxableGood::class.java)
        .with(schema.withSkipFirstDataRow(true))
        .readValues<TaxableGood>(inputStream)
        .readAll()

fun OutputStream.writeCsv(goods: List<TaxableGood>) {
    csvMapper.writer().with(schema.withHeader()).writeValues(this).writeAll(goods)
}