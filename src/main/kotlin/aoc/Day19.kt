package aoc


sealed class Rule {
    abstract fun consume(code: String, all: Map<Int, Rule>): List<String>

    companion object {
        private val simpleRulePatter = """(\d+): "(\w)"""".toRegex()

        fun parse(str: String): Pair<Int, Rule> {
            return if (simpleRulePatter.matches(str)) {
                val (idx, pattern) = simpleRulePatter.find(str)!!.destructured
                idx.toInt() to Simple(pattern)
            } else {
                val (idx, rest) = str.split(":")
                idx.toInt() to OrRule(rest.split("|").map { or ->
                    or.split(" ").filter { it.isNotEmpty() }.map { ruleId -> ruleId.toInt() }
                })
            }
        }
    }
}

data class Simple(val same: String) : Rule() {
    override fun consume(code: String, all: Map<Int, Rule>): List<String> {
        return if (code.startsWith(same)) listOf(code.substring(same.length))
        else emptyList()
    }

}

data class OrRule(val rules: List<List<Int>>) : Rule() {
    override fun consume(code: String, all: Map<Int, Rule>): List<String> {
        val res: List<List<String>> = rules.mapNotNull {
            it.fold(listOf(code)) { acc: List<String>?, ruleId: Int ->
                if (acc != null && acc.isNotEmpty()) {
                    acc.flatMap { remaining ->
                        all.getValue(ruleId).consume(remaining, all)
                    }
                } else null
            }
        }
        return res.flatten()
    }

}

object Day19 {
    fun matches(data: List<String>): Int {
        val (rStr, lines) = data.split { it.isEmpty() }
        val rules: Map<Int, Rule> = rStr.map { Rule.parse(it) }.toMap()
        return lines.filter {
            rules.getValue(0).consume(it, rules).any { remainingMessage -> remainingMessage.isEmpty() }
        }
            .size
    }
}
