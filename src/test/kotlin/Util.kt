import java.io.File

fun readLines(fileName: String): List<String> = File("src/test/resources/$fileName").readLines()