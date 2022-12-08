package org.elwaxoro.advent.y2022

import org.elwaxoro.advent.PuzzleDayTester

class Dec08 : PuzzleDayTester(8, 2022) {

    override fun part1(): Any {
        val grid = loadTreesIntoGrid()

        var visibleCount = 0
        grid.forEachIndexed { rowIdx, row ->
            row.forEachIndexed { colIdx, _ ->
                if (searchLeft(grid, rowIdx, colIdx)
                    || searchRight(grid, rowIdx, colIdx)
                    || searchUp(grid, rowIdx, colIdx)
                    || searchDown(grid, rowIdx, colIdx)
                ) visibleCount += 1
            }
        }
        return visibleCount
    }

    private fun searchLeft(grid: Array<IntArray>, rowIdx: Int, colIdx: Int): Boolean {
        var colIdxMutable = colIdx
        while (colIdxMutable > 0) {
            if (grid[rowIdx][colIdxMutable - 1] >= grid[rowIdx][colIdx]) {
                return false
            }
            colIdxMutable--
        }
        return true
    }
    private fun searchRight(grid: Array<IntArray>, rowIdx: Int, colIdx: Int): Boolean {
        var colIdxMutable = colIdx
        while (colIdxMutable < grid[rowIdx].size - 1) {
            if (grid[rowIdx][colIdxMutable + 1] >= grid[rowIdx][colIdx]) {
                return false
            }
            colIdxMutable++
        }
        return true
    }

    private fun searchUp(grid: Array<IntArray>, rowIdx: Int, colIdx: Int): Boolean {
        var rowIdxMutable = rowIdx
        while (rowIdxMutable > 0) {
            if (grid[rowIdxMutable - 1][colIdx] >= grid[rowIdx][colIdx]) {
                return false
            }
            rowIdxMutable--
        }
        return true
    }

    private fun searchDown(grid: Array<IntArray>, rowIdx: Int, colIdx: Int): Boolean {
        var rowIdxMutable = rowIdx
        while (rowIdxMutable < grid.size - 1) {
            if (grid[rowIdxMutable + 1][colIdx] >= grid[rowIdx][colIdx]) {
                return false
            }
            rowIdxMutable++
        }
        return true
    }

    private fun loadTreesIntoGrid(): Array<IntArray> = load().let { rows -> Array(rows.size) {IntArray(size = rows[0].length)} to rows }.let { (grid, rows) ->
        rows.onEachIndexed { idx,row ->
            row.forEachIndexed { colIdx, char -> grid[idx][colIdx] = char.digitToInt() }
        }
        grid
    }
//    .also {
//        for (row in it) {
//            println(row.contentToString())
//        }
//    }

}