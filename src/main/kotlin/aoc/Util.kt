package aoc_2020

import java.util.function.Predicate


fun <E> List<E>.split(keepMatchingLine: Boolean = false, predicate: Predicate<E>): List<List<E>> {
    val result = mutableListOf<List<E>>()
    var tmp = mutableListOf<E>()
    forEach {
        if (predicate.test(it)) {
            result.add(tmp)
            tmp = mutableListOf()
            if (keepMatchingLine) tmp.add(it)
        } else {
            tmp.add(it)
        }
    }
    return result.apply { add(tmp) }

}