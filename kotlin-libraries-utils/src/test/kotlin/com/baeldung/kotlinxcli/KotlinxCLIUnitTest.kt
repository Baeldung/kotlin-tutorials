package com.baeldung.kotlinxcli

import kotlinx.cli.*
import kotlinx.cli.default
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class KotlinxCLIUnitTest {
    @Test
    fun `when required user name is not passed than throw error`() {
        val args: Array<String> = arrayOf("-n", "John")
        val parser = ArgParser("example")
        val name by parser.option(
            ArgType.String, shortName = "n", description = "User name"
        ).required()
        parser.parse(args)
        assertEquals("John", name)
    }

    @Test
    fun `when user name arguments not passed added than name is null`() {
        val args: Array<String> = emptyArray()
        val parser = ArgParser("example")
        val name by parser.option(
            ArgType.String, shortName = "n", description = "User name"
        )
        parser.parse(args)
        assertNull(name)
    }

    @Test
    fun `when user name have no arguments is added than store it default value in name`() {
        val args: Array<String> = emptyArray()
        val parser = ArgParser("example")
        val name by parser.option(
            ArgType.String, shortName = "n", description = "User name"
        ).default("N/A")
        parser.parse(args)
        assertEquals("N/A", name)
    }

    @Test
    fun `when multiple user name arguments is added than store it in name as list`() {
        val args: Array<String> = arrayOf("-n", "John", "-n", "Smith")
        val parser = ArgParser("example")
        val names by parser.option(
            ArgType.String, shortName = "n", description = "User name"
        ).multiple()
        parser.parse(args)
        assertEquals(listOf("John", "Smith"), names)
    }

    @Test
    fun `when choice is given from enum than store it in format as enum`() {
        val args: Array<String> = arrayOf("-f", "csv")
        val parser = ArgParser("example")
        val format by parser.option(
            ArgType.Choice<Format>(), shortName = "f", description = "Format for the output file"
        )
        parser.parse(args)
        assertEquals(Format.CSV, format)
    }

    @Test
    fun `when custom choice is given than store it in format`() {
        val args: Array<String> = arrayOf("-sf", "pdf")
        val parser = ArgParser("example")
        val typeFormat by parser.option(
            ArgType.Choice(listOf("html", "csv", "pdf"), { it }),
            shortName = "sf",
            description = "Format as a string for the output file"
        )
        parser.parse(args)
        assertEquals("pdf", typeFormat)
    }

    @OptIn(ExperimentalCli::class)
    @Test
    fun `when subcommand are is given than store it in multiply`() {
        val args: Array<String> = arrayOf("mul", "1", "2", "3")
        val parser = ArgParser("example")
        val multiple = Multiply()
        parser.subcommands(multiple)
        parser.parse(args)
        assertEquals(6, multiple.result);
    }

    @OptIn(ExperimentalCli::class)
    @Test
    fun `when subcommand is combined with outer argument than store it in total`() {
        val args: Array<String> = arrayOf("-o", "4", "mul", "1", "2", "3")
        val parser = ArgParser("example")
        val outNumber by parser.option(ArgType.Int, "outer number", "o").default(0)
        val multiple = Multiply()
        parser.subcommands(multiple)
        parser.parse(args)
        val total = outNumber.plus(multiple.result)
        assertEquals(10, total)
    }
}

@OptIn(ExperimentalCli::class)
class Multiply : Subcommand("mul", "Multiply") {
    private val numbers by argument(ArgType.Int, description = "Numbers").multiple(3)
    var result = 0
    override fun execute() {
        result = numbers.reduce { acc, it -> acc * it }
    }
}

enum class Format {
    HTML, CSV, PDF
}