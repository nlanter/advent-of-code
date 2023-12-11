package org.elwaxoro.advent.y2023

import org.elwaxoro.advent.PuzzleDayTester

class Dec1: PuzzleDayTester(1, 2023)  {
    override fun part1(): Any = load(testNum = 1).map { it.toCharArray() }.calcDigits()

    override fun part2() = load().map { str ->
        var newStr = str
       charStringMap.onEach {
           newStr = newStr.replace(it.key, it.key + it.value.toString() + it.key)
//           println(newStr)
       }
        newStr.toCharArray()
    }.calcDigits()

    private fun List<CharArray>.calcDigits() = map {
        it.filter { char -> char.isDigit() }
    }.fold(0) { acc, next ->
        val curr = ("${next.firstOrNull() ?: ""}${next.lastOrNull() ?: ""}").toInt()
//        println("$next -> $curr = ${acc + curr}")
        acc + curr
    }

    val charStringMap = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9
    )
}
