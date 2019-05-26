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
//Вынести чтение файла в отдельный блок
//Перепроверить систему шифрования битов
//stringBuilder к строке
//Генератор случайного текста для тестов
//
// Task 3
// Двумерная карта с клетками, лабиринт, перемещение, взаимодействие с клетками, найти набор вещей - вытащить.
// Генерирует лабиринт, ивенты, квик тайм ивенты,
class EncryptionXOR{
    fun decryption(key: String, outputText: String, inputText: String) {
        File(inputText).delete()
        File(inputText).createNewFile()
        val msg1 = File(outputText).readText()
        var msg = ""
        for (line in msg1) msg += line
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
        File(output).delete()
        File(output).createNewFile()
        val inputTextTemp = File(msg).readText()
        val outputFile = File(output)
        var inputText = ""
        for (line in inputTextTemp) {
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
        if (args[3] != "-o") {
            File(args[3]).createNewFile()
            return EncryptionXOR().decryption(args[1], args[2], args[3])
        }
        return EncryptionXOR().decryption(args [1],args [2],args[4])
    }
    if (args[0] ==  "-c")  {
        if (args[3] != "-o") {
            File(args[3]).createNewFile()
            return EncryptionXOR().encryption(args[1], args[2], args[3])
        }
        return EncryptionXOR().encryption(args [1],args [2],args[4])
    }
}