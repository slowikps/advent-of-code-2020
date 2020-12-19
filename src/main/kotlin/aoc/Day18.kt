package aoc

import aoc.Day18.solution3
import java.lang.IllegalStateException
import java.util.*


sealed class Element {
    companion object {
        fun from(input: Stack<String>): PriorityOp {
            fun internal(priorOp: PriorityOp): PriorityOp {
                val next = input.pop()
                when {
                    next == "(" -> {
                        priorOp.add(internal(PriorityOp()))
                    }
                    next == ")" -> return priorOp
                    next.toLongOrNull() != null -> priorOp.add(Number(next.toLong()))
                    else -> priorOp.add(Operation(next))
                }
                return if (input.isEmpty()) priorOp
                else internal(priorOp)
            }
            return internal(PriorityOp())
        }
    }

    abstract fun asString(): String
}

class Operation(private val op: String) : Element() {
    override fun asString(): String = op
}

class Number(val n: Long) : Element() {
    override fun asString(): String = "$n"
}

class PriorityOp(private val elements: MutableList<Element> = mutableListOf()) : Element() {

    fun add(element: Element) = elements.add(element)

    fun solve(): Number {
        return Number(solution3(elements.map {
            if (it is PriorityOp) it.solve().asString()
            else it.asString()
        }.foldRight(Stack<String>()) { next, acc ->
            acc.push(next)
            acc
        }))
    }

    override fun asString(): String = throw IllegalStateException("Shouldn't be called")
}

object Day18 {

    fun solution2(inputData: Stack<String>): Long {
        return Element.from(inputData).solve().n
    }

    fun solution3(stack: List<String>): Long {
        return stack.split { it == "*" }
            .map {
                it.fold(Stack<String>()) { acc, next ->
                    acc.push(next)
                    acc
                }
            }.map {
                solution1(it)
            }.fold(1L) { acc, next ->
                acc * next
            }
    }

    fun solution1(inputData: Stack<String>): Long {
        fun internal(numbers: Stack<Long>, operators: Stack<String>): Long {
            if (inputData.isEmpty() && operators.isEmpty()) return numbers.pop()
            else if (numbers.size > 1) {
                val operator = operators.pop()
                val second = numbers.pop()
                numbers.push(operation(operator, numbers.pop(), second))
            } else {
                val next = inputData.pop()
                if (next.toLongOrNull() != null) {
                    numbers.push(next.toLong())
                } else if (next == "+" || next == "*") {
                    operators.push(next)
                } else if (next == "(") {
                    numbers.push(internal(Stack(), Stack()))
                } else {
                    return numbers.pop()
                }
            }
            return internal(numbers, operators)
        }

        return internal(Stack(), Stack())
    }

    private fun operation(operator: String, first: Long, second: Long): Long =
        if (operator == "+") first + second
        else first * second
}

