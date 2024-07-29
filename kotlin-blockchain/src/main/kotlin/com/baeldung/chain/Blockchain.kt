package com.baeldung.chain

class Blockchain(difficulty: Int = 5) {
    val chain = mutableListOf<Block>()
    val acceptanceRegex = "^[0]{$difficulty}.+".toRegex();

init {
    val genesisBlock = mineBlock("0", "Genesis Block", acceptanceRegex)
    chain.add(genesisBlock)
}

    fun addBlock(block: Block) {
        if (isValidBlock(block)) {
            chain.add(block)
        } else {
            throw IllegalArgumentException("Invalid block")
        }
    }

    private fun isValidBlock(block: Block): Boolean {
        val lastBlock = chain.last()
        return block.previousHash == lastBlock.hash &&
                block.hash.matches(acceptanceRegex)
    }

    fun isValid(): Boolean {
        if (chain.size < 2) return true

        chain.zipWithNext().forEach { (prev, current) ->
            if (current.previousHash != prev.hash) {
                return false
            }
        }
        return true
    }
}