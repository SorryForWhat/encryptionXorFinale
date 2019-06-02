package src
//Reading progressively difficult texts can improve researchers' English language competency
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals
class Test {
    @Test
    fun encryptor(){
        EncryptionXOR("123", "src/res/inputText.txt", "src/res/outputText.txt").encryptAndDecrypt()
    }
    @Test
    fun decryptor(){
        EncryptionXOR("123", "src/res/outputText.txt", "src/res/inputText.txt").encryptAndDecrypt()
    }
    @Test
    fun check() {
        val startText = File("src/res/inputText.txt").readText()
        EncryptionXOR("123", "src/res/inputText.txt", "src/res/outputText.txt").encryptAndDecrypt()
        File("src/res/inputText.txt").delete()
        File("src/res/inputText.txt").createNewFile()
        println()

        EncryptionXOR("123", "src/res/outputText.txt", "src/res/inputText.txt").encryptAndDecrypt()
        File("src/res/outputText.txt").delete()
        File("src/res/outputText.txt").createNewFile()
        val decText = File("src/res/inputText.txt").readText()
        assertEquals(decText, startText)
    }
}