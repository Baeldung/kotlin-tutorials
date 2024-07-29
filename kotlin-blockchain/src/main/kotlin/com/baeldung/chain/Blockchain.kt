package com.baeldung.chain

class Blockchain(private val difficulty: Int = 5) {
    val chain = mutableListOf<Block>()

    init {
        val genesisBlock = Block("0", "Genesis Block")
        genesisBlock.mineBlock(difficulty)
        chain.add(genesisBlock)
    }

    fun addBlock(data: String) {
        val previousHash = chain.last().hash
        val newBlock = Block(previousHash, data)
        newBlock.mineBlock(difficulty)
        chain.add(newBlock)
    }

    fun isValid(): Boolean {
        if (chain.size < 2) return true

        chain.zipWithNext().forEach { (prev, current) ->
            if (current.previousHash != prev.hash || current.hash != current.calculateHash()) {
                return false
            }
        }
        return true
    }
}