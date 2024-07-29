package com.baeldung.chain

import java.security.MessageDigest

data class Block(
    val previousHash: String,
    val data: String,
    val timestamp: Long = System.currentTimeMillis(),
    var nonce: Int = 0
) {
    var hash: String = calculateHash()

    fun calculateHash(): String {
        val input = "$previousHash$data$timestamp$nonce"
        return input.sha256()
    }

    fun mineBlock(difficulty: Int) {
        while (!hash.startsWith("0".repeat(difficulty))) {
            nonce++
            hash = calculateHash()
        }
    }
}

fun String.sha256(): String {
    val bytes = this.toByteArray()
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(bytes)
    return digest.fold("") { str, it -> str + "%02x".format(it) }
}