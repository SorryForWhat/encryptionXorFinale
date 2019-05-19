package src

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.Exception
import java.lang.IllegalArgumentException
import kotlin.test.assertTrue

class Test {
    @Test
    fun encryptor(){
        EncryptionXOR().encryption("123", "src/res/inputText.txt", "src/res/outputText.txt")
    }
    @Test
    fun decryptor(){
        EncryptionXOR().decryption("123", "src/res/outputText.txt", "src/res/inputText.txt")
    }
}