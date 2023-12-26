package com.baeldung.code.covered

import org.junit.Test

class CoveredClassTest {

    @Test
    fun add() {
        CoveredClass().add(1, 2)
    }

    @Test
    fun multiply() {
        CoveredClass().multiply(1, 2)
    }
}