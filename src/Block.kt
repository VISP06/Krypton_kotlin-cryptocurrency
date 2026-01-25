import java.time.Instant
/*
 A data class in Kotlin is a special type of class designed to hold data,
 automatically generating useful methods like
 equals(), hashCode(), and toString() based on the properties defined in its primary constructor
 */
//it is considered best practice to make the data members of a class private and that is done auto by the class parameters i.e '( )'
data class Block(
    val previousHash: String, //This is the "Magic Stamp" of the page that came right before this one. It’s how the blocks stay connected in a chain.
    val data: String, //This is the actual information you want to store (like "Alice sent 10 coins to Bob").
    val timestamp: Long = Instant.now().toEpochMilli(), //This is the exact time (in milliseconds) the block was born.
    val nonce: Long = 0,
    var hash: String = ""
                    ) {
    init {
        hash = calculateHash()
    }

    fun calculateHash(): String {
        return "$previousHash$data$timestamp".hash()
    }
    /*
      fun calculateHash():
      It takes the previousHash, the data, and the
      timestamp and squashes them together into one long string.
      It then calls .hash(), that turns the big pile of text into a unique 64-character code
     */
    companion object {
        fun create(previousHash: String, data: String): Block {
            return Block(previousHash = previousHash, data = data)
        }
    }
}

/*
TL;DR->default setup that boots up as soon as the code is run without doing any operations
init { ... }:-
 This is the "Setup" block. As soon as you create a new block,
 the computer immediately runs the code inside here to calculate the stamp (hash) so the page isn't blank.
 */

/*
TL;DR -> they basically static members
Companion objects: in Kotlin are special objects that are associated with a class and can hold properties
and methods that are accessible without creating an instance of the class.
They act similarly to static members in other programming languages
 */