package aoc

import java.util.function.Predicate


fun <E> List<E>.split(predicate: Predicate<E>): List<List<E>> {
    val result = mutableListOf<List<E>>()
    var tmp = mutableListOf<E>()
    forEach {
        if (predicate.test(it)) {
            result.add(tmp)
            tmp = mutableListOf()
        } else {
            tmp.add(it)
        }
    }
    return result.apply { add(tmp) }

}