class BlockChain {
    private var blocks: MutableList<Block> = mutableListOf()
    //private because only the BlockChain class can touch the list directly. It’s like keeping the diary in a locked drawer so no one can rip out pages.
    //This is a simple function that takes a Block you’ve already made and sticks it into the blocks list.
    fun add(block: Block): Block{
        val minedBlock = if(isMined(block)) block else mine(block)
        blocks.add(minedBlock)
        return minedBlock
    }
    fun getChain(): List<Block> {
        return blocks
    }

    private val difficulty = 5
    private val validPrefix = "0".repeat(difficulty)

    private fun isMined(block: Block): Boolean{
        return block.hash.startsWith(validPrefix)
    }

    private fun mine(block: Block): Block{
        println("Mining: $block")

        var minedBlock = block.copy()
        while(!isMined(minedBlock)){
            minedBlock = minedBlock.copy(nonce = minedBlock.nonce+1)
        }
        println("Mined: $minedBlock")
        return minedBlock
    }

    fun isValid(): Boolean {
        // 1. Check if the chain is empty (an empty chain is technically valid)
        if (blocks.isEmpty()) return true

        // 2. Check if every single block is correctly mined
        val allBlocksMined = blocks.all { isMined(it) }
        if (!allBlocksMined) return false

        // 3. Check the "Chain" links
        // .zipWithNext() compares (Block 0 with Block 1), then (Block 1 with Block 2), etc.
        val linksValid = blocks.zipWithNext().all { (prev, curr) ->
            curr.previousHash == prev.hash
        }

        return linksValid
    }
}

/*
* var minedBlock = block.copy() -> In Kotlin, data classes are usually immutable.
  So, instead of changing the original block, we make a copy of it to work on.
* while (!isMined(minedBlock)): This is a loop that keeps running as long as the stamp is "wrong".

* nonce = minedBlock.nonce + 1: Every time the loop runs, it creates a new copy with the nonce number increased by 1.

* It keeps doing this—changing the number and re-checking the stamp—until it finally finds a combination that starts with "00000".
*/