package org.elwaxoro.advent.y2024

import org.elwaxoro.advent.PuzzleDayTester

class Dec5: PuzzleDayTester(5, 2024)  {

    //5955
    override fun part1(): Any = loadToString().split("\n\n").let { (rules, pages) ->
        val ruleMap = mutableMapOf<Int, MutableList<Int>>() // list of everything which must be before that number
        rules.split("\n").onEach {
            val split = it.split("|")
            if (ruleMap[split[1].toInt()] != null) {
                ruleMap[split[1].toInt()]?.add(split[0].toInt())
            } else {
                ruleMap[split[1].toInt()] = mutableListOf(split[0].toInt())
            }
        }

        pages.split("\n").map { it.split(",") }.filter { line ->
            line.mapIndexed { idx, str ->
                line.drop(idx).none { ruleMap[str.toInt()]?.contains(it.toInt()) == true}
            }.all { it }
        }.sumOf {
            it[it.size / 2].toInt()
        }

    }
}
