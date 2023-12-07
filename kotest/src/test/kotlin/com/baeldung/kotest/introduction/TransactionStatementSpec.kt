package com.baeldung.kotest.introduction

import io.kotest.core.spec.style.ShouldSpec

class TransactionStatementSpec : ShouldSpec({
    beforeTest {
        // add transactions
    }
    afterTest { (test, result) ->
        // delete transactions
    }
})