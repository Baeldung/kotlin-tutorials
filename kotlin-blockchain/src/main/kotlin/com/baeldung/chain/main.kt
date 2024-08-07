package com.baeldung.chain

fun main() {
    val blockchain = Blockchain()

    blockchain.addBlock("Block 1 Data")
    blockchain.addBlock("Block 2 Data")
    blockchain.addBlock("Block 3 Data")

    println("Blockchain valid? ${blockchain.isValid()}")

    blockchain.chain.forEach {
        println("Block Data: ${it.data}, Hash: ${it.hash}")
    }
}