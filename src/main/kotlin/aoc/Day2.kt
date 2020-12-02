package aoc

object Day2 {

    private val lineRegex = """(\d+)-(\d+) ([a-zA-Z]): (\w+)""".toRegex()

    fun isLineValid(line: String) =
        isLineValid(parseLine(line))

    fun isLineValid2(line: String) =
        isLineValid2(parseLine(line))

    fun parseLine(line: String): ParsedLine {
        val (min, max, letter, password) = lineRegex.find(line)!!.destructured

        return ParsedLine(
            letter = letter.first(),
            minOcc = min.toInt(),
            maxOcc = max.toInt(),
            password = password
        )
    }

    private fun isLineValid(line: ParsedLine): Boolean =
        line.password.count { it == line.letter }.let {
            it <= line.maxOcc && it >= line.minOcc
        }

    private fun isLineValid2(line: ParsedLine): Boolean {
        val l1 = line.password[line.minOcc - 1]
        val l2 = line.password[line.maxOcc - 1]
        return l1 != l2 && (l1 == line.letter || l2 == line.letter)
    }
}

data class ParsedLine(
    val letter: Char,
    val minOcc: Int,
    val maxOcc: Int,
    val password: String
)