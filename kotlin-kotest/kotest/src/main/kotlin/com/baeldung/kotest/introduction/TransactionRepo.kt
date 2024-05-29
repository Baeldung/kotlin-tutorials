package com.baeldung.kotest.introduction

class TransactionRepo {
    fun getStatus(transactionId: Int): String {
        return "COMPLETE"
    }
}