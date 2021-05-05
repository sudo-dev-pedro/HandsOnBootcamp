package br.com.handson5

import java.io.File
import java.io.InputStream
import java.nio.charset.Charset

fun main() {

    val fileName = "E:\\Projetos\\HandsOnBootcamp\\HandsOnBootcamp\\HandsOn5-6\\app\\src\\test\\java\\br\\com\\handson5\\movieResponse.json"
    val myFile = File(fileName)

    val ins: InputStream = myFile.inputStream()

    val content = ins.readBytes().toString(Charset.defaultCharset())

    println(content)
}