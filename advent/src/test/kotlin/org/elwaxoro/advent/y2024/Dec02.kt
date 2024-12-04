package org.elwaxoro.advent.y2024

import org.elwaxoro.advent.PuzzleDayTester
import kotlin.math.absoluteValue

class Dec2: PuzzleDayTester(2, 2024)  {
    //680
    override fun part1(): Any = load().getSafeCount()

    private fun List<String>.getSafeCount() = map { row ->
        if (isSafeRow(row)) 1 else 0
    }.sum()

    private fun isSafeRow(row: String): Boolean {
        val cols = row.split(" ").map { it.toInt() }

        val increasingOrDecreasing = cols[1] > cols[0]
        return cols.mapIndexed { i, num ->
            if (i == 0)
                true
            else
                (cols[i] > cols[i - 1] && increasingOrDecreasing || cols[i] < cols[i - 1] && !increasingOrDecreasing) && (num - cols[i - 1]).absoluteValue <= 3
        }.all { it }
    }

    // 710
    override fun part2(): Any = load().partition { isSafeRow(it) }.let { (safe, unsafe) ->
        unsafe.map { it.split(" ") }.filter { originalRow ->
            originalRow.mapIndexed { index, _ ->
                val newList = originalRow.toMutableList()
                newList.removeAt(index)
                isSafeRow(newList.joinToString(" "))
            }.any { it }
        }.plus(safe).count()
    }
}
