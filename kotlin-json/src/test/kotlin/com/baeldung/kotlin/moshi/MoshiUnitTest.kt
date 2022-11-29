package com.baeldung.kotlin.moshi

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.runBlocking
import okio.buffer
import okio.source
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.InputStream
import java.math.BigDecimal
import java.util.UUID
import kotlin.test.assertNotNull

@ExperimentalStdlibApi
internal class MoshiUnitTest {
    val moshi: Moshi = Moshi.Builder()
        .add(UuidAdapter())
        .add(BigDecimalAdapter())
        .add(HexadecimalAdapter())
        .addLast(KotlinJsonAdapterFactory())
        .build()!!

    @Test
    fun `when serialising a data class then no annotation is needed only some adapters`() {

        val salaryRecordJsonAdapter = moshi.adapter<SalaryRecord>()

        val record = SalaryRecord(
            "John",
            "Doe",
            UUID.fromString("A7C6C66F-1531-46E0-ABAB-232704667FF4"),
            "Sales",
            BigDecimal("869463.32"),
            BigDecimal("0.39")
        )
        val serialized = salaryRecordJsonAdapter.toJson(record)
        assertEquals(
            resource("single_salary_record.json").reader().readText(),
            serialized
        )
    }

    @Test
    fun `when deserialising a data class then its the same story as with serialising`() {
        val departmentJsonAdapter = moshi.adapter<Department>()
        val department =
            departmentJsonAdapter.fromJson(resource("sales_department.json").source().buffer())
        assertNotNull(department)
        assertEquals("Sales", department.name)
        assertEquals(3, department.employees.size)
    }

    @Test
    fun `when deserialising a list of objects then reified types help`() {
        val employeeListAdapter = moshi.adapter<List<Employee>>()
        val list =
            employeeListAdapter.fromJson(resource("list_of_employees.json").source().buffer())
        assertNotNull(list)
        assertEquals(3, list.size)
        assertEquals(listOf("John", "Jane", "Max"), list.map { it.firstName })
    }

    @Test
    fun `when the name is modified or a field ignored then the coder complies`() {
        val snakeProjectAdapter = moshi.adapter<SnakeProject>()
        val snakeProject = SnakeProject(
            snakeProjectName = "Mayhem",
            snakeResponsiblePersonName = "Tailor Burden",
            snakeProjectBudget = BigDecimal("100000000"),
            snakeProjectSecret = "You're not a snowflake"
        )
        val stringProject = snakeProjectAdapter.toJson(snakeProject)
        assertEquals(resource("snake_project.json").reader().readText(), stringProject)
    }

    @Test
    fun `when reading from a flow then reading one object at a time`() {
        val employeeAdapter = moshi.adapter<Employee>()
        resource("list_of_employees.json").let {
            val totalSalary = runBlocking {
                readToFlow(it, employeeAdapter)
                    .fold(BigDecimal.ZERO) { acc, value -> acc + value.salary }
            }
            assertEquals(BigDecimal("1505080.05"), totalSalary)
        }
    }

    @Test
    fun `when serializing annotated values then the format is altered`() {
        val paletteAdapter = moshi.adapter<Palette>()
        val palette = Palette(
            83 * 256 * 256 + 43 * 256 + 18,
            160 * 256 + 18,
            25
        )
        assertEquals(
            "{\"mainColor\":\"#532b12\",\"backgroundColor\":\"#00a012\",\"frameFrequency\":25}",
            paletteAdapter.toJson(palette)
        )
    }

    companion object {
        private fun resource(resourceName: String): InputStream =
            MoshiUnitTest::class.java.getResourceAsStream(resourceName)!!
    }
}