package org.elwaxoro.advent.y2022

import org.elwaxoro.advent.PuzzleDayTester

class Dec03 : PuzzleDayTester(3, 2022) {

    override fun part1(): Any =
        loadData().map { pair -> pair.first.filter { c -> pair.second.contains(c) }.toSet().first().toInt() }
            .sumOf { if (it > 90) it - 96 else it - 38 }

    fun loadData(): List<Pair<String, String>> {
        return load().map { it.substring(0, it.length / 2) to it.substring(it.length / 2, it.length) }
    }

    override fun part2(): Any = loadData2().map { list -> list.first().filter { c -> list[1].contains(c) && list[2].contains(c) }.toSet().first().toInt() }
        .sumOf { if (it > 90) it - 96 else it - 38 }

    fun loadData2(): List<List<String>> = load().windowed(3, step = 3)


}