package org.elwaxoro.advent.y2022

import org.elwaxoro.advent.PuzzleDayTester
import org.elwaxoro.advent.toInt
import java.util.*

class Dec04 : PuzzleDayTester(4, 2022) {
    private val reggy = "(\\d{1,2})-(\\d{1,2}),(\\d{1,2})-(\\d{1,2})".toRegex()

    override fun part1(): Any = loadData().count { pair ->
            pair.first.all { it in pair.second } || pair.second.all { it in pair.first }
    }
    override fun part2(): Any = loadData().count { pair ->
        pair.first.any { it in pair.second } || pair.second.all { it in pair.first }
    }

    fun loadData() = load().map { pairData ->
        reggy.matchEntire(pairData)?.let {
            (it.groups[1].toInt() .. it.groups[2].toInt()) to (it.groups[3].toInt() .. it.groups[4].toInt())
        } ?: throw java.lang.IllegalStateException("wut")
    }

}