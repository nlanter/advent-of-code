package org.elwaxoro.advent.y2022

import org.elwaxoro.advent.PuzzleDayTester

class Dec08 : PuzzleDayTester(8, 2022) {

    override fun part1(): Any {
        val grid = loadTreesIntoGrid()

        var visibleCount = 0
        grid.forEachIndexed { rowIdx, row ->
            row.forEachIndexed { colIdx, _ ->
                if (searchLeftV2(grid, rowIdx, colIdx).second
                    || searchRightV2(grid, rowIdx, colIdx).second
                    || searchUpV2(grid, rowIdx, colIdx).second
                    || searchDownV2(grid, rowIdx, colIdx).second
                ) visibleCount += 1
            }
        }
        return visibleCount
    }

    override fun part2(): Any {
        val grid = loadTreesIntoGrid()

        var maxVisibleCount = 0
        grid.forEachIndexed { rowIdx, row ->
            row.forEachIndexed { colIdx, _ ->
                val visibility = searchLeftV2(grid, rowIdx, colIdx).first * searchRightV2(grid, rowIdx, colIdx).first * searchUpV2(grid, rowIdx, colIdx).first * searchDownV2(grid, rowIdx, colIdx).first
                if (visibility > maxVisibleCount) maxVisibleCount = visibility
            }
        }
        return maxVisibleCount
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



    private fun searchLeftV2(grid: Array<IntArray>, rowIdx: Int, colIdx: Int): Pair<Int, Boolean> {
        var colIdxMutable = colIdx
        var viewingDistance = 0
        while (colIdxMutable > 0) {
            if (grid[rowIdx][colIdxMutable - 1] >= grid[rowIdx][colIdx]) {
                return viewingDistance + 1 to false
            }
            colIdxMutable--
            viewingDistance++
        }
        return viewingDistance to true
    }
    private fun searchRightV2(grid: Array<IntArray>, rowIdx: Int, colIdx: Int): Pair<Int, Boolean> {
        var colIdxMutable = colIdx
        var viewingDistance = 0
        while (colIdxMutable < grid[rowIdx].size - 1) {
            if (grid[rowIdx][colIdxMutable + 1] >= grid[rowIdx][colIdx]) {
                return viewingDistance + 1 to false
            }
            colIdxMutable++
            viewingDistance++
        }
        return viewingDistance to true
    }

    private fun searchUpV2(grid: Array<IntArray>, rowIdx: Int, colIdx: Int): Pair<Int, Boolean> {
        var rowIdxMutable = rowIdx
        var viewingDistance = 0

        while (rowIdxMutable > 0) {
            if (grid[rowIdxMutable - 1][colIdx] >= grid[rowIdx][colIdx]) {
                return viewingDistance + 1 to false
            }
            rowIdxMutable--
            viewingDistance++
        }
        return viewingDistance to true
    }

    private fun searchDownV2(grid: Array<IntArray>, rowIdx: Int, colIdx: Int): Pair<Int, Boolean> {
        var rowIdxMutable = rowIdx
        var viewingDistance = 0

        while (rowIdxMutable < grid.size - 1) {
            if (grid[rowIdxMutable + 1][colIdx] >= grid[rowIdx][colIdx]) {
                return viewingDistance + 1 to false
            }
            rowIdxMutable++
            viewingDistance++
        }
        return viewingDistance to true
    }


}