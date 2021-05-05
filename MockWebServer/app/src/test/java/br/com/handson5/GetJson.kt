package br.com.handson5

import java.io.File
import java.io.InputStream
import java.nio.charset.Charset

class JsonReader {

    companion object {
        fun getJson(path: String): String {
            val myFile = File(path)

            val ins: InputStream = myFile.inputStream()

            return ins.readBytes().toString(Charset.defaultCharset())
        }
    }
}