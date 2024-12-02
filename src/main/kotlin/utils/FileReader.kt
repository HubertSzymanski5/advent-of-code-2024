package pl.szymanski.hubert.utils

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

fun readFile(fileName: String): List<String> {
    return Files.lines(getPath(fileName)).toList()
}

private fun getPath(fileName: String): Path {
    return Paths.get("src/main/resources/$fileName")
}