
class Main {
    fun main(args: Array<String>){
        val gensisBlock = Block.create(previousHash = "0", data="I'm the first")
        val secondBlock = Block.create(gensisBlock.hash, data="I am here second")
        val thirdBlock = Block.create(secondBlock.hash, "I am here third")

        println(gensisBlock)
        println(secondBlock)
        println(thirdBlock)
    }
}