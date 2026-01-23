import java.math.BigInteger
import java.security.MessageDigest

fun String.hash(algorithm: String = "SHA-256"): String{
    val messageDigest = MessageDigest.getInstance(algorithm)
    messageDigest.update(this.toByteArray())
    return String.format("%064x", BigInteger(1, messageDigest.digest()))
}

/*
* String.hash -> is the extension function syntax, by prefixing with String. you are adding the method to the existing string class
* algorithm: String = "SHA-256" -> where algorithm is the parameter with a default value. When you call this method it used SHA-256 by default unless u specify a different encryption algo
* :String -> is the return type
* this keyword -> holds a reference to the object so it's like doing "hello".hash()
* .toByteArray() -> is used to convert the string into a sequence of bytes for cryptographic processing

*MessageDigest.getInstance(algorithm) -> This initializes the Java security engine for the chosen algorithm (SHA-256 by default).
*messageDigest.update(...) -> This feeds your data (the bytes of your string) into the hashing engine.
*messageDigest.digest() -> This performs the actual mathematical calculation and returns a ByteArray (the raw binary hash).
*BigInteger(1, ...) -> This converts the raw binary hash into a large positive number. The 1 specifies that the number should be positive.
*String.format("%064x", ...) -> This converts that large number into a hexadecimal string. The %064x ensures it is exactly 64 characters long, padding with zeros if necessary.
*/