import java.util.Scanner

private var inputText: Scanner? = null
class EncoderXOR {
    fun decryption() {
        print("Enter message: ")
        val msg = inputText!!.nextLine()
        print("Enter key: ")
        val key = inputText!!.nextLine()
        var hexToPairs = ""
        run {
            var i = 0
            while (i < msg.length - 1) {
                //Разделяем по сетку по нескольким парам(xor операция)
                val output = msg.substring(i, i + 2)
                val decimal = Integer.parseInt(output, 16)
                hexToPairs += decimal.toChar()
                i += 2
            }
        }
        var decryptedText = ""
        var keyItr = 0
        for (i in 0 until hexToPairs.length) {
            val temp = hexToPairs[i]
            decryptedText += temp.toChar()
            keyItr++
            if (keyItr >= key.length) {
                keyItr = 0
            }

        }

        println("Decrypted Text: $decryptedText")
    }

    fun encryption() {
        print("Enter message: ")
        val msg = inputText!!.nextLine()
        print("Enter key: ")
        val key = inputText!!.nextLine()
        var encryptedText = ""
        var keyItr = 0
        for (i in 0 until msg.length) {
            //Разделяем по сетку по нескольким парам(xor операция)
            val temp = msg[i]
            encryptedText += String.format("%02x", temp.toByte())
            keyItr++
            if (keyItr >= key.length) {
                keyItr = 0
            }

        }
        println("Encrypted Text: $encryptedText")
    }
}
fun main(args: Array<String>) {
        inputText = Scanner(System.`in`)
        print("Choose Operation(1,2):\n1. Encryption\n2. Decryption\n ")
        val choice = inputText!!.nextInt()
        inputText!!.nextLine()

    when (choice) {
        1 -> {
            println("===Encryption===")
            EncoderXOR().encryption()
        }
        2 -> {
            println("===Decryption===")
            EncoderXOR().decryption()
        }
        else -> println("Invalid Choice")
    }
    }

