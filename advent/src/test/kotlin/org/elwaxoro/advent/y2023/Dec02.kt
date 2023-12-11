package org.elwaxoro.advent.y2023

import org.elwaxoro.advent.PuzzleDayTester

class Dec02: PuzzleDayTester(2, 2023)  {
    override fun part1(): Any = load(testNum = 1).toGames().filter {
        it.sets.all {
            it.color == CubeColor.RED && it.count <= 12 ||
            it.color == CubeColor.BLUE && it.count <= 14 ||
            it.color == CubeColor.GREEN && it.count <= 13
        }
    }.sumOf { it.num }

    override fun part2() = load(testNum = 2).toGames()
        .map { game ->
            val maxOfEachColor = game.sets.groupBy { it.color }.map { (_, sets) -> sets.maxBy { it.count } }
            maxOfEachColor.fold(1) { acc, next ->
                acc * next.count
            }
        }.sum()

    fun List<String>.toGames() = mapIndexed { idx, gameStr ->
        val game = Game(num = idx + 1)
        gameStr.split(";").onEach { setStr ->
            setStr.split(", ").onEach { colorStr ->
                game.addColor(colorStr.trimStart())
            }
        }
        game
    }

    data class Game(val num: Int, val sets: MutableList<CubeSet> = mutableListOf()) {
        fun addColor(colorStr: String) = colorStr.split(" ").also {(count, color) -> sets.add(CubeSet(CubeColor.valueOf(color.toUpperCase().trim()), count.toInt()))}

        override fun toString(): String {
            return "$num: ${sets.joinToString("; ") { "$it" } }"
        }
    }

    data class CubeSet(val color: CubeColor, var count: Int) {
        override fun toString(): String {
            return "$count $color"
        }

    }

    enum class CubeColor {
        RED, GREEN, BLUE
    }
}
