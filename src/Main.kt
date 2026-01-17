fun main() {
    val lumenChain = BlockChain()
    /*
    println("--- Starting the Blockchain ---")

    // Adding the first block (The Genesis Block)
    println("Adding Block 1...")
    lumenChain.add(Block(previousHash = "0", data = "Genesis Block: 100 Coins to VISP06"))

    // Adding a second block
    println("\nAdding Block 2...")
    lumenChain.add(Block(previousHash = lumenChain.getChain().last().hash, data = "Alice sends 10 coins to Bob"))

    // Adding a third block
    println("\nAdding Block 3...")
    lumenChain.add(Block(previousHash = lumenChain.getChain().last().hash, data = "Bob sends 5 coins to Charlie"))

    println("\n--- Final Blockchain State ---")
    lumenChain.getChain().forEach { block ->
        println("Hash: ${block.hash} | Nonce: ${block.nonce} | Data: ${block.data}")
    }
    */
    val genesisBlock = Block.create(previousHash = "0", data = "I'm the first")
    val secondBlock = Block.create(genesisBlock.hash, "I'm the second")
    val thirdBlock = Block.create(secondBlock.hash, "I'm the third")

    println(genesisBlock)
    println(secondBlock)
    println(thirdBlock)
}