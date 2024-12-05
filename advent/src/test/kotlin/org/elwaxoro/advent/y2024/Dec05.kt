package org.elwaxoro.advent.y2024

import org.elwaxoro.advent.PuzzleDayTester

class Dec5 : PuzzleDayTester(5, 2024) {

    //5955
    override fun part1(): Any = splitGoodAndBad().first.sumOf {
        it[it.size / 2]
    }

    // 4030
    override fun part2(): Any {
        val rules = getRulesAndPages(testNum = 2).first
        return splitGoodAndBad(testNum = 2).second.map {
            it.sortedWith { a, b ->
                if (rules[a.toInt()]?.contains(b.toInt()) == true) -1 else 0
            }
        }.sumOf {
            it[it.size / 2]
        }
    }

    private fun splitGoodAndBad(testNum: Int? = null): Pair<List<List<Int>>, List<List<Int>>> = getRulesAndPages(testNum = testNum).let { (rules, pages) ->
        pages.split("\n").map { it.split(",").map { it.toInt() } }.partition { line ->
            line.mapIndexed { idx, str ->
                line.drop(idx).none { rules[str]?.contains(it) == true }
            }.all { it }
        }
    }


    private fun getRulesAndPages(testNum: Int? = null): Pair<MutableMap<Int, MutableList<Int>>, String> = loadToString(testNum = testNum).split("\n\n").let { (rules, pages) ->
        val ruleMap = mutableMapOf<Int, MutableList<Int>>() // list of everything which must be before that number
        rules.split("\n").onEach {
            val split = it.split("|")
            if (ruleMap[split[1].toInt()] != null) {
                ruleMap[split[1].toInt()]?.add(split[0].toInt())
            } else {
                ruleMap[split[1].toInt()] = mutableListOf(split[0].toInt())
            }
        }
        return ruleMap to pages
    }
}
