package org.elwaxoro.advent.y2022

import org.elwaxoro.advent.PuzzleDayTester

class Dec04: PuzzleDayTester(4, 2022) {

    override fun part1(): Any = rangeLoader().count { (a, b) ->
        a.intersect(b).size.let {
            it == a.count() || it == b.count()
        }
    }// == 651

    override fun part2(): Any = rangeLoader().count { (a, b) ->
        a.intersect(b).isNotEmpty()
    }// == 956

    private fun rangeLoader(): List<List<IntRange>> = load().map { line ->
        line.split(",").map { range ->
            range.split("-").map { it.toInt() }.let { (start, end) ->
                IntRange(start, end)
            }
        }
    }
}
