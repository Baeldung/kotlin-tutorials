package com.baeldung.chain

fun main() {
    val blockchain = Blockchain(3)

    val block1 = mineBlock(blockchain.chain.last().hash, "Block 1 Data", blockchain.acceptanceRegex)
    blockchain.addBlock(block1)

    val block2 = mineBlock(blockchain.chain.last().hash, "Block 2 Data", blockchain.acceptanceRegex)
    blockchain.addBlock(block2)

    val block3 = mineBlock(blockchain.chain.last().hash, "Block 3 Data", blockchain.acceptanceRegex)
    blockchain.addBlock(block3)

    println("Blockchain valid? ${blockchain.isValid()}")

    blockchain.chain.forEach {
        println("Block Data: ${it.data}, Hash: ${it.hash}")
    }
}