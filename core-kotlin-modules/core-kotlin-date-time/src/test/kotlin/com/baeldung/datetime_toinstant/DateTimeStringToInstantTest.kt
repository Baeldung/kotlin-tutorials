package com.baeldung.datetime_toinstant

import org.junit.jupiter.api.Test
import java.time.*
import java.time.format.DateTimeFormatter
import kotlin.test.assertEquals

class DateTimeStringToInstantTest {

    @Test
    fun `method one combining date and time string and parsing to localdatetime and using zoneId`(){
        var strDate = "2021-11-25"
        var strTime = "15:20"
        var strDateTime = strDate + "T" + strTime
        val ldt = LocalDateTime.parse(strDateTime)
        val instant: Instant = ldt.atZone(ZoneId.systemDefault()).toInstant()
        assertEquals("2021-11-25T14:20:00Z", instant.toString())
    }

    @Test
    fun `method two by parsing date and time string to localdatetime and using zoneId`(){
        var strDate = "2021-11-25"
        var strTime = "15:20"
        val ldt = LocalDateTime.of(LocalDate.parse(strDate), LocalTime.parse(strTime))
        val instant: Instant = ldt.atZone(ZoneId.systemDefault()).toInstant()
        assertEquals("2021-11-25T14:20:00Z", instant.toString())
    }

    @Test
    fun `method 3 using zoneddatetime object`(){
        var strDate = "2021-11-25"
        var strTime = "15:20"
        val zdt1 = ZonedDateTime.of(LocalDate.parse(strDate), LocalTime.parse(strTime),ZoneId.systemDefault() )
        val zdt2 = ZonedDateTime.of(LocalDateTime.of(LocalDate.parse(strDate), LocalTime.parse(strTime)), ZoneId.systemDefault())
        val instant1: Instant = zdt1.toInstant()
        val instant2: Instant = zdt2.toInstant()
        assertEquals("2021-11-25T14:20:00Z", instant1.toString())
        assertEquals("2021-11-25T14:20:00Z", instant2.toString())
    }

    @Test
    fun `method four using zoneddatetime and datetimeformatter`(){
        val strDate = "2021-11-25"
        val strTime = "15:20"
        val dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.systemDefault())
        val zdt = ZonedDateTime.parse(strDate + "T" + strTime, dtf)
        val instant = zdt.toInstant()
        assertEquals("2021-11-25T14:20:00Z", instant.toString())
    }

    @Test
    fun `method five using toinstant method directly on localdatetime`(){
        val strDate = "2021-11-25"
        val strTime = "15:20"
        val ldt = LocalDateTime.of(LocalDate.parse(strDate), LocalTime.parse(strTime))
        val instant = ldt.toInstant(ZoneId.systemDefault().rules.getOffset(ldt))
        assertEquals("2021-11-25T14:20:00Z", instant.toString())
    }
}