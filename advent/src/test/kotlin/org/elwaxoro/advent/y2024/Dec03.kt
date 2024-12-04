package org.elwaxoro.advent.y2024

import org.elwaxoro.advent.PuzzleDayTester
import kotlin.math.absoluteValue

class Dec3: PuzzleDayTester(3, 2024)  {
    val regex = Regex("(mul\\(\\d+,\\d+\\))")
    val dontRegex = Regex("don't\\(\\)(.|\\n)*?do\\(\\)")

    override fun part1(): Any = doPartOne(loadToString())

    // 159892596
    private fun doPartOne(string: String) = regex.findAll(string).sumOf { mul ->
            mul.value.replace("mul(", "").replace(")", "").split(",").let { (a, b) -> a.toInt() * b.toInt() }
        }

    // substring to remove any don't that might be at the end without a do
    // 92626942
    override fun part2(): Any = dontRegex.replace(loadToString(testNum=2)) {""}.substringBefore("don't()").let {
        doPartOne(it)
    }
}
