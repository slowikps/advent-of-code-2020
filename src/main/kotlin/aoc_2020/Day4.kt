package aoc_2020

import java.util.function.Predicate

object Day4 {

    private val requiredTokensPartOne: Map<String, Predicate<String>> = mapOf(
        "byr" to Predicate { true },
        "iyr" to Predicate { true },
        "eyr" to Predicate { true },
        "hgt" to Predicate { true },
        "hcl" to Predicate { true },
        "ecl" to Predicate { true },
        "pid" to Predicate { true }
    )

    private val hgtRegex = """(\d+)(cm|in)""".toRegex()
    private val hclRegex = """#[0-9a-f]{6}""".toRegex()
    private val pidRegex = """\d{9}""".toRegex()

    private val requiredTokensPartTwo: Map<String, Predicate<String>> = mapOf(
        "byr" to Predicate { it.toInt() in 1920..2002 },
        "iyr" to Predicate { it.toInt() in 2010..2020 },
        "eyr" to Predicate { it.toInt() in 2020..2030 },
        "hgt" to Predicate {
            hgtRegex.find(it)?.let { matchResult ->
                val (hgh, unit) = matchResult.destructured
                when (unit) {
                    "cm" -> hgh.toInt() in 150..193
                    "in" -> hgh.toInt() in 59..76
                    else -> false
                }
            } ?: false
        },
        "hcl" to Predicate { hclRegex.matches(it) },
        "ecl" to Predicate { it in setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth") },
        "pid" to Predicate { pidRegex.matches(it) }
//        "cid",
    )

    fun validPassports(lines: List<String>): Int =
        tokenize(lines, requiredTokensPartOne).fold(0) { acc, set ->
            acc + if (set.containsAll(requiredTokensPartOne.keys)) 1 else 0
        }

    fun validPassportsPart2(lines: List<String>): Int =
        tokenize(lines, requiredTokensPartTwo).fold(0) { acc, set ->
            acc + if (set.containsAll(requiredTokensPartTwo.keys)) 1 else 0
        }


    private fun tokenize(lines: List<String>, predicates: Map<String, Predicate<String>>): List<Set<String>> {
        val result = mutableListOf<Set<String>>()
        var passportFields = mutableSetOf<String>()
        lines.forEach { line ->
            if (line.isBlank()) {
                result += passportFields
                passportFields = mutableSetOf()
            } else {
                line.split(" ").forEach { token ->
                    val (name, value) = token.split(":")
                    if (predicates[name]?.test(value) == true) {
                        passportFields.add(name)
                    }
                }
            }
        }
        result += passportFields
        return result
    }

}
