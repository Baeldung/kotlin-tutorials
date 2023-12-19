package com.baeldung.ktorm

import org.ktorm.schema.BaseTable
import org.ktorm.schema.SqlType
import java.nio.charset.Charset
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Types
import java.util.*

class Base64String(private val charset: Charset): SqlType<String>(Types.VARCHAR, "base64") {

    override fun doGetResult(rs: ResultSet, index: Int): String? {
        val retrievedData = rs.getString(index)

        return when {
            retrievedData.isNullOrBlank() -> null
            else -> Base64.getDecoder().decode(retrievedData).toString(charset)
        }
    }

    override fun doSetParameter(ps: PreparedStatement, index: Int, parameter: String) {
        ps.setString(index, Base64.getEncoder().encodeToString(parameter.toByteArray(charset)))
    }
}

fun BaseTable<*>.base64(name: String, charset: Charset) = registerColumn(name, Base64String(charset))