class BlockChain {
    private var blocks: MutableList<Block> = mutableListOf()

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