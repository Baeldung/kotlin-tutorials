package com.baeldung.chain

import java.security.MessageDigest

data class Block(
    val previousHash: String,
    val data: String,
    val timestamp: Long = System.currentTimeMillis(),
    val nonce: Int = 0
) {

    val hash: String by lazy { calculateHash() }

    fun calculateHash(): String {
        val input = "$previousHash$data$timestamp$nonce"
        return input.sha256()
    }
}

fun mineBlock(previousHash: String, data: String, acceptingRegexp: Regex): Block {
    var finalBlock = Block(previousHash, data)
    while (!finalBlock.hash.matches(acceptingRegexp)) {
        finalBlock = finalBlock.copy(nonce = finalBlock.nonce + 1)
    }
    return finalBlock
}

fun String.sha256(): String {
    val bytes = this.toByteArray()
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(bytes)
    return digest.fold("") { str, it -> str + "%02x".format(it) }
}