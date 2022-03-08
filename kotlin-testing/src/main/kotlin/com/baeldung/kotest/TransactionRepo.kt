package com.baeldung.kotest

class TransactionRepo {
    fun getStatus(transactionId: Int): String {
        return "COMPLETE"
    }
}