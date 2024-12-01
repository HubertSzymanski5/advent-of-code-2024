package pl.szymanski.hubert.utils

import java.io.File

fun readFile(fileName: String): List<String> {
    return getFile(fileName).readLines()
}

private fun getFile(fileName: String): File {
    return File("src/main/resources/$fileName")
}