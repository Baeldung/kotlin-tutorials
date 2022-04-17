package com.baeldung.kotlin.collection.ops

import java.net.URL
import java.util.UUID

data class Transaction(val id: UUID, var state: TransactionState = TransactionState.RUNNING) {
    enum class TransactionState { RUNNING, COMMITTED, ROLLEDBACK }

    fun rollback() {
        state = TransactionState.ROLLEDBACK
    }

    fun commit() {
        state = TransactionState.COMMITTED
    }
}

data class Database(val url: URL, val credentials: String)

inline fun <T> inTransaction(db: Database, action: (Database) -> T): T {
    val transaction = Transaction(id = UUID.randomUUID())
    try {
        return action(db).also { transaction.commit() }
    } catch (ex: Exception) {
        transaction.rollback()
        throw ex
    }
}

fun transactedAction(arg: Any): String {
    val database = Database(URL("http://localhost"), "user:pass")
    return inTransaction(database) { UUID.randomUUID().toString() + arg.toString() }
}
