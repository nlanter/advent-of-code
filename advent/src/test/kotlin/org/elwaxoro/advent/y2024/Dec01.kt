package org.elwaxoro.advent.y2024

import org.elwaxoro.advent.PuzzleDayTester
import kotlin.math.absoluteValue

class Dec1: PuzzleDayTester(1, 2024)  {
    //1873376
    override fun part1(): Any = loadLists().let { (listOne, listTwo) ->
        listOne.zip(listTwo).sumOf { (one, two) -> (one - two).absoluteValue }
    }

    //18997088
    override fun part2(): Any = loadLists().let { (listOne, listTwo) ->
        listOne.sumOf { one ->
            one * listTwo.count { it == one }
        }
    }

    private fun loadLists() = load().let { loaded ->
        val listOne = mutableListOf<Int>()
        val listTwo = mutableListOf<Int>()

        loaded.onEach { row ->
            val (one, two) = row.split("  ")
            listOne.add(one.trim().toInt())
            listTwo.add(two.trim().toInt())
        }

        listOne.sort()
        listTwo.sort()
        listOne to listTwo
    }
}
