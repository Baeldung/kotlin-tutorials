package com.baeldung.code.covered

import org.junit.jupiter.api.Test

class CoveredClassUnitTest {

    @Test
    fun add() {
        CoveredClass().add(1, 2)
    }

    @Test
    fun multiply() {
        CoveredClass().multiply(1, 2)
    }

    @Test
    fun max() {
        CoveredClass().max(10, 5)
    }

    // Leaving this test commented out so we can see what an uncovered class looks like in the report
//    @Test
//    fun min() {
//        CoveredClass().min(10, 5)
//    }
}