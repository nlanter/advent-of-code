package org.elwaxoro.advent.y2023

import org.elwaxoro.advent.PuzzleDayTester

class Dec15 : PuzzleDayTester(15, 2023) {

    //108792
    override fun part1(): Any = load(testNum = 1).map { row -> row.toCharArray().toList() }.tilt().let { table ->
        table.mapIndexed { idx, row ->
            row.count { it == 'O' } * (table.size - idx)
        }.sum()
    }

    private fun List<List<Char>>.tilt(): List<List<Char>> {
        val reversedMutable = reversed().toMutableList().map { it.toMutableList() }
            reversedMutable.onEachIndexed { rowIdx, cols ->
            if (rowIdx < reversedMutable.lastIndex) {
                cols.onEachIndexed { colIdx, col ->
                    if (reversedMutable.next(rowIdx, colIdx) != '#' && col == 'O') {
                        move(reversedMutable, rowIdx, colIdx)
                    }
                }
//                println()
//                println("after row $rowIdx\n${reversedMutable.reversed().joinToString("\n")}")
            }
        }
//        println()
//        println("finished\n${reversedMutable.reversed().joinToString("\n")}")
        return reversedMutable.reversed()
    }

    fun List<List<Char>>.next(rowIdx: Int, colIdx: Int) = ('#').takeIf { rowIdx + 1 > this.lastIndex } ?: this[rowIdx + 1][colIdx]

    private fun move(reversedMutable: List<MutableList<Char>>, rowIdx: Int, colIdx: Int) {
        var rowIdxV = rowIdx
        val el = reversedMutable[rowIdx][colIdx]
        while (rowIdxV < reversedMutable.lastIndex && reversedMutable.next(rowIdxV, colIdx) != '#') {
            val tmp = reversedMutable.next(rowIdxV, colIdx)
            reversedMutable[rowIdxV + 1][colIdx] = el
            reversedMutable[rowIdxV][colIdx] = tmp
            rowIdxV = rowIdxV + 1
            if (reversedMutable.next(rowIdxV, colIdx) == '#' && reversedMutable[rowIdx][colIdx] == 'O') {
                // we initially swapped a O in our row, restart the process on this row to move that up too
                if (!(rowIdx..rowIdxV).all { reversedMutable[it][colIdx] == 'O' }) {
                    // only reset if not all the rows are already Os, otherwise nowhere to go
                    rowIdxV = rowIdx
                }
            }
//            println()
//            println("after swap $rowIdx\n${reversedMutable.reversed().joinToString("\n")}")
        }
    }
}

