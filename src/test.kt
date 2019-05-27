package src

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.File
import java.lang.Exception
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import java.util.Random

//const val length = 10;
//const val charPool = "[a-zA-Z0-9]+"
//val randomString = (1..length)
//        .map { i -> kotlin.random.Random.nextInt(0, charPool.size) }
//        .map(charPool::get)
//        .joinToString("")
class Test {
    @Test
    fun encryptor(){
        EncryptionXOR("123", "src/res/inputText.txt", "src/res/outputText.txt").encryption()
    }
    @Test
    fun decryptor(){
        EncryptionXOR("123", "src/res/outputText.txt", "src/res/inputText.txt").decryption()
    }
    @Test
    fun check() {
        val startText = File("src/res/inputText.txt").readText()
        EncryptionXOR("123", "src/res/inputText.txt", "src/res/outputText.txt").encryption()
        File("src/res/inputText.txt").delete()
        File("src/res/inputText.txt").createNewFile()
        EncryptionXOR("123", "src/res/outputText.txt", "src/res/inputText.txt").decryption()
        File("src/res/outputText.txt").delete()
        File("src/res/outputText.txt").createNewFile()
        val decText = File("src/res/inputText.txt").readText()
        assertEquals(decText, startText)
    }
}