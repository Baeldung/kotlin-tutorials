package com.baeldung.railwayorientedprogramming

import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Test

class ROPUnitTest {
    @Test
    fun `create and save customer successfully`() {;
        val customer = Customer(
            "Zeeshan Arif Saeed", "zeesh.arif@gmail.com"
        )
        val res = ROP.createAndSave(
            arrayOf(
                "Zeeshan Arif Saeed", "zeesh.arif@gmail.com"
            )
        )
        assertInstanceOf(
            res::class.java,
            Success("Customer successfully saved: " + customer)
        )
    }

    @Test
    fun `create and save customer name validatation failure`() {;
        val customer = Customer(
            "Zeeshan", "zeesh.arif@gmail.com"
        )
        val res = ROP.createAndSave(
            arrayOf(
                "Zeeshan", "zeesh.arif@gmail.com"
            )
        )
        assertInstanceOf(
            res::class.java,
            Failure<String>("Name validation failed. Name must be greater than 10 characters.")
        )
    }

    @Test
    fun `create and save customer email validatation failure`() {;
        val customer = Customer(
            "Zeeshan Arif Saeed", "zeesh.arif@gmail.com"
        )
        val res = ROP.createAndSave(
            arrayOf(
                "Zeeshan", "zeesh.arifgmail.com"
            )
        )
        assertInstanceOf(
            res::class.java,
            Failure<String>("Email validation failed. Email must contain '@' symbol.")
        )
    }
}