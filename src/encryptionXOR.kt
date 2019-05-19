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
package src
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

class EncryptionXOR{
    fun decryption(key: String, outputText: String, inputText: String) {
        val msg1 = File(outputText).readLines()
        var msg = ""
        for (line in msg1) {
            msg += line
        }
            val output = File(inputText)
            val writer = BufferedWriter(FileWriter(output, true))
            var hexToPairs = ""
            var i = 0
            while (i < msg.length) {
                //Разделяем по нескольким парам(xor операция)
                val new = msg.substring(i, i + 2)
                val decimal = new.toInt(16)
                hexToPairs += decimal.toChar()
                i += 2
            }
            var keyItr = 0
            for (i in 0 until hexToPairs.length) {
                val temp = hexToPairs[i].toInt() xor key[keyItr].toInt()
                writer.write(temp.toChar().toString())
                keyItr++
                if (keyItr >= key.length) {
                    keyItr = 0
                }

            }
        writer.close()
        }

    fun encryption(key: String, msg: String, output: String) {
        val inputText1 = File(msg).readLines()
        val outputFile = File(output)
        var inputText = ""
        for (line in inputText1) {
            inputText += line
        }
        val writer = BufferedWriter(FileWriter(outputFile, true))
        var keyItr = 0
        for (i in 0 until inputText.length) {
            //Разделяем сетку по нескольким парам(xor операция)
            val temp = inputText[i].toInt() xor key[keyItr].toInt()
            writer.write(String.format("%02x", temp.toByte()))
            keyItr++
            if (keyItr >= key.length) {
                keyItr = 0
            }
        }
        writer.close()
    }
}
fun main(args: Array<String>) {
    if (args[0] ==  "-d")  {
        return EncryptionXOR().decryption(args [1],args [2],args[4])
    }
    if (args[0] ==  "-c")  {
        return EncryptionXOR().encryption(args [1],args [2],args[4])
    }
}