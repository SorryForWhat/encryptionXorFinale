//Итак, основная идея алгоритма состоит в том, что если у нас есть некая величина,
//есть некий шифровальный ключ (другая величина), то можно зашифровать исходные данные через этот ключ
//применив операцию XOR побитно. Т.е. если у нас есть исходная фраза a и ключ k, то x = a ^ k.
//Теперь, если к шифру x опять применить ключ, то получим исходную фразу, т.е. a` = x ^ k, где a` = a
/**
 * Вариант 2 -- шифрация Шифрация (-c) или дешифрация (-d) указанного (в аргументе командной строки) файла.
 * Выходной файл указывается как -o filename.txt, по умолчанию имя формируется из имени входного файла
 * с добавлением расширения. Алгоритм шифрации XOR. Ключ указывается после -c или -d в шестнадцатеричной системе,
 * длина ключа -- любое целое количество байт.
Command Line: ciphxor [-c key] [-d key] inputname.txt [-o outputname.txt]
Кроме самой программы, следует написать автоматические тесты к ней.
 */
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

//import org.kohsuke.args4j

//class parser {
//    @Option( name = "-c", usage = "", forbids = ["-c"])
//    var z: Boolean = false
//}



//fun stringBuilder(key: String, input: String, output: String) {
//    val inputText = File(input).readLines()
//    val outputFile = File(output)
//    val result: List<String>
//    result = (inputText, key)
//    val writer = BufferedWriter(FileWriter(outputFile, true))
//    for (i in result)
//        writer.write(i)
//    writer.close()
//}

class EncoderXOR {
    fun decryption() {
        print("Enter message: ")
        val msg = readLine()!!
        print("Enter key: ")
        val key = readLine()!!
        var hexToPairs = ""
        var i = 0
        while (i < msg.length - 1) {
            //Разделяем по нескольким парам(xor операция)
            val output = msg.substring(i, i + 2)
            val decimal = output.toInt(16)
            hexToPairs += decimal.toChar() //stringBuilder
            i += 2
        }
        var decryptedText = ""
        var keyItr = 0
        for (i in 0 until hexToPairs.length) {
            val temp = hexToPairs[i].toInt() xor key[keyItr].toInt()
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
        val msg = readLine()!!
        print("Enter key: ")
        val key = readLine()!!
        var encryptedText = ""
        var keyItr = 0
        for (i in 0 until msg.length) {
            //Разделяем сетку по нескольким парам(xor операция)
            val temp = msg[i].toInt() xor key[keyItr].toInt()
            encryptedText += String.format("%02x", temp.toByte())//stringBuilder
            keyItr++
            if (keyItr >= key.length) {
                keyItr = 0
            }
        }
        println("Encrypted Text: $encryptedText")
    }
}
fun main(args: Array<String>) {
    print("Choose Operation(-c,-d):\n-c. Encryption\n-d. Decryption\n ")
    val choice = readLine()!!
    when (choice) {
        "-c" -> {
            println("===Encryption===")
            EncoderXOR().encryption()
        }
        "-d" -> {
            println("===Decryption===")
            EncoderXOR().decryption()
        }
        else -> println("Invalid Choice")
    }
}