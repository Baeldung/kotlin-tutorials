package com.baeldung.kotlin.moshi

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.buffer
import okio.source
import java.io.InputStream
import java.math.BigDecimal
import java.util.UUID

@JsonClass(generateAdapter = true)
data class Department(
    val name: String,
    val code: UUID,
    val employees: List<Employee>
)

data class Employee(
    val firstName: String,
    val lastName: String,
    val title: String,
    val age: Int,
    val salary: BigDecimal
)

data class SalaryRecord(
    val employeeFirstName: String,
    val employeeLastName: String,
    val departmentCode: UUID,
    val departmentName: String,
    val sum: BigDecimal,
    val taxPercentage: BigDecimal
)

data class SnakeProject(
    @Json(name = "project_name")
    val snakeProjectName: String,
    @Json(name = "responsible_person_name")
    val snakeResponsiblePersonName: String,
    @Json(name = "project_budget")
    val snakeProjectBudget: BigDecimal,
    @Json(ignore = true)
    val snakeProjectSecret: String = "No secret"
)

class UuidAdapter : JsonAdapter<UUID>() {
    @FromJson
    override fun fromJson(reader: JsonReader): UUID? = UUID.fromString(reader.readJsonValue().toString())

    @ToJson
    override fun toJson(writer: JsonWriter, value: UUID?) {
        writer.jsonValue(value.toString())
    }
}

class BigDecimalAdapter : JsonAdapter<BigDecimal>() {
    @FromJson
    override fun fromJson(reader: JsonReader): BigDecimal? = reader.readJsonValue()?.let { BigDecimal(it.toString()) }

    @ToJson
    override fun toJson(writer: JsonWriter, value: BigDecimal?) {
        writer.jsonValue(value?.toPlainString())
    }

}

suspend inline fun <reified T> readToFlow(input: InputStream, adapter: JsonAdapter<T>): Flow<T> = flow {
    JsonReader.of(input.source().buffer())
        .readArray {
            emit(adapter.fromJson(this)!!)
        }
}

inline fun JsonReader.readArray(body: JsonReader.() -> Unit) {
    beginArray()
    while (hasNext()) {
        body()
    }
    endArray()
}

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class Hexadecimal

class HexadecimalAdapter {
    @ToJson
    fun toJson(@Hexadecimal color: Int): String = "#%06x".format(color)

    @FromJson
    @Hexadecimal
    fun fromJson(color: String): Int = color.substring(1).toInt(16)
}

data class Palette(
    @Hexadecimal val mainColor: Int,
    @Hexadecimal val backgroundColor: Int,
    val frameFrequency: Int
)
