package com.baeldung.kotest.testcontainers

import javax.sql.DataSource

class DatabaseService(
    val dataSource: DataSource
) {
    fun insert(person: Person) {
        dataSource.connection.createStatement().execute("""
            CREATE TABLE IF NOT EXISTS Person(PersonName VARCHAR(255) NOT NULL)
        """.trimIndent())
        dataSource.connection.createStatement().execute("""
            INSERT INTO Person(PersonName) VALUES ('${person.name}')
        """.trimIndent())
    }

    fun all(): List<Person> {
        val rs = dataSource.connection.createStatement().executeQuery("SELECT * FROM Person")
        val list = mutableListOf<Person>()
        while(rs.next()) {
            val name = rs.getString("PersonName")
            list += Person(name)
        }
        rs.close()
        return list
    }
}

data class Person(val name: String)