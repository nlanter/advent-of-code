package org.elwaxoro.advent.y2022

import org.elwaxoro.advent.PuzzleDayTester

class Dec03 : PuzzleDayTester(3, 2022) {

    override fun part1(): Any = loadData().map {pair -> pair.first.filter { c -> pair.second.contains(c) }.toSet().first().toInt()}.map {if (it > 90) it - 96 else it - 38}.sum()

    fun loadData(): List<Pair<String, String>> {
        return load().map { it.substring(0, it.length / 2) to it.substring(it.length / 2, it.length) }
    }


}