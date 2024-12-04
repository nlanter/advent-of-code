package org.elwaxoro.advent.y2024

import org.elwaxoro.advent.PuzzleDayTester
import kotlin.math.absoluteValue

class Dec3: PuzzleDayTester(3, 2024)  {
    val regex = Regex("(mul\\(\\d+,\\d+\\))")
    override fun part1(): Any = regex.findAll(loadToString()).sumOf { mul ->
        mul.value.replace("mul(", "").replace(")", "").split(",").let { (a, b) -> a.toInt() * b.toInt() }
    }
}
