import java.security.PrivateKey
import java.security.PublicKey
import java.security.KeyPairGenerator

data class Wallet(
    val publicKey: PublicKey,
    val privateKey: PrivateKey,
    val blockChain: BlockChain
    ){
    fun getBalance(): Int{
        var total = 0 ;
        for(item in myTransactions){
            total += item.amount
        }
        return total
    }
    private fun myTransactions(): List<TransactionsOutput>{
        val myOutputs = mutableListOf<TransactionOutput>()
        for(item in blockChain.UTXO.values){
            if(item.isMine(publicKey)){
                myOutputs.add(item)
            }
        }
        return myOutputs
    }
    companion object{
        fun createWallet(blockChain: BlockChain): Wallet {
            // This part is the "Magic Key Generator"
            val generator = KeyPairGenerator.getInstance("RSA")
            generator.initialize(2048)
            val keyPair = generator.generateKeyPair()

            // We take the new keys and put them into a new Wallet object
            return Wallet(keyPair.public, keyPair.private, blockChain)
        }
    }
}
