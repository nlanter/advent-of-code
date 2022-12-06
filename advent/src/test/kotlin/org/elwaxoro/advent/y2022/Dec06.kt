package org.elwaxoro.advent.y2022

import org.elwaxoro.advent.PuzzleDayTester
import java.util.*

class Dec06 : PuzzleDayTester(6, 2022) {

    override fun part1(): Any = findUniqueChars(4)
    override fun part2(): Any = findUniqueChars(14)

    private fun findUniqueChars(windowSize: Int) = load()[0].windowed(size = windowSize, step = 1).indexOfFirst { str ->
        str.toSet().size == windowSize
    } + windowSize

}