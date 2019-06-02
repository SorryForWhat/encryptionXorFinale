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

class EncryptionXOR(tempKey: String, inputText: String, outputText: String){
    private val msg = File(inputText).readText()
    private val outputFile = File(outputText)
    private val writer = BufferedWriter(FileWriter(outputFile, true))
    private val key = tempKey
    fun encryptAndDecrypt() {
        for (i in 0 until msg.length) {
            val temp = (msg[i].toInt() xor key[i % key.length].toInt()).toChar()
            writer.write(temp.toString())
            }
        writer.close()
    }
}
fun main(args: Array<String>) {
    if (args[0] ==  "-d")  {
        if (args[3] != "-o") return EncryptionXOR(args[1], args[2], args[3]).encryptAndDecrypt()
        File(args[4]).forEachLine { println (it) }
    }
}