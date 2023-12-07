package com.baeldung

import com.baeldung.jooq.codegen.models.tables.Company
import com.baeldung.jooq.codegen.models.tables.Flight
import com.baeldung.jooq.codegen.models.tables.references.AIRCRAFT
import org.jooq.DSLContext
import org.jooq.conf.ParamType
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.init.ScriptUtils
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.event.annotation.BeforeTestClass
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [ApplicationConfiguration::class])
class JOOQWithKotlinIntegrationTest {

    @Autowired
    lateinit var dslContext: DSLContext

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    @BeforeEach
    fun beforeTestClassListener() {
        ScriptUtils.executeSqlScript(
            jdbcTemplate.dataSource?.connection ?: throw IllegalStateException(), ClassPathResource("schema-init.sql")
        )
    }

    @Test
    fun testWritingData() {
        val sql = dslContext.insertInto(Company.COMPANY)
            .columns(Company.COMPANY.ID, Company.COMPANY.NAME, Company.COMPANY.ADDRESS)
            .values(1L, "SomeAirlines", "Solar system, Mars")
            .getSQL(ParamType.INLINED)

        jdbcTemplate.execute(sql)
    }

    @Test
    fun testReadingDataSimpleQuery() {
        val query = dslContext.select(AIRCRAFT.field(AIRCRAFT.ID))
            .from(AIRCRAFT)
            .where(AIRCRAFT.SERIAL_NUMBER.eq("123456"))
            .getSQL(ParamType.INLINED)

        jdbcTemplate.queryForObject(query, Long::class.java)
    }

    @Test
    fun testReadingDataMoreComplexQuery() {
        val query = dslContext.select(Flight.FLIGHT.ID)
            .from(Flight.FLIGHT)
            .innerJoin(AIRCRAFT).on(AIRCRAFT.ID.eq(Flight.FLIGHT.AIRCRAFT_ID))
            .innerJoin(Company.COMPANY).on(Company.COMPANY.ID.eq(AIRCRAFT.COMPANY_ID))
            .where(Company.COMPANY.NAME.eq("Airlines"))
            .and(Flight.FLIGHT.DEPARTURE_AT.ge(LocalDateTime.of(LocalDate.of(2023, 1, 1), LocalTime.of(1, 0))))
            .getSQL(ParamType.INLINED)

        jdbcTemplate.queryForObject(query, Long::class.java)
    }
}