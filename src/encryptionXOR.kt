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
import java.lang.StringBuilder

//Вынести чтение файла в отдельный блок+
//Перепроверить систему шифрования битов
//stringBuilder к строке+
//Генератор случайного текста для тестов
//
// Task 3
// Двумерная карта с клетками, лабиринт, перемещение, взаимодействие с клетками, найти набор вещей - вытащить.
// Генерирует лабиринт, ивенты, квик тайм ивенты,
//            val charset = Charsets.UTF_8
//            val byteArray = msg.toByteArray(charset)
//            byteArray.forEach { hexToPairs += it}

class EncryptionXOR(tempKey: String, inputText: String ,outputText: String){
    private val inputTextTemp = File(inputText).readText()
    private val outputFile = File(outputText)
    private val writer = BufferedWriter(FileWriter(outputFile, true))
    private val key = tempKey
    private var msg = ""
    fun decryption() {
        for (line in inputTextTemp) msg += line
        var hexToPairs = StringBuilder("")
        var i = 0
        while (i < msg.length) {
            //Разделяем по нескольким парам(xor операция)
            val new = msg.substring(i, i + 2)
            val decimal = new.toInt(16)
            hexToPairs.append(decimal.toChar())
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
    fun encryption() {
        for (line in inputTextTemp) msg += line
        var keyItr = 0
        for (i in 0 until msg.length) {
            //Разделяем сетку по нескольким парам(xor операция)
            val temp = msg[i].toInt() xor key[keyItr].toInt()
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
        if (args[3] != "-o") {
            return EncryptionXOR(args[1], args[2], args[3]).decryption()
        }
        File(args[4]).forEachLine { println (it) }
    }
    if (args[0] ==  "-c")  {
        if (args[3] != "-o") {
            return EncryptionXOR(args[1], args[2], args[3]).encryption()
        }
        File(args[4]).forEachLine { println (it) }
    }
}