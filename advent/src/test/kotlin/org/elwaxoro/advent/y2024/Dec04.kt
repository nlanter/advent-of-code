package org.elwaxoro.advent.y2024

import org.elwaxoro.advent.PuzzleDayTester

class Dec4: PuzzleDayTester(4, 2024)  {

    val xmas = listOf('x', 'm', 'a', 's')
    override fun part1(): Any = load().map { it.lowercase().toCharArray() }.let { map ->
        var count = 0
         map.onEachIndexed { rowIdx, row ->
            row.onEachIndexed { colIdx, col ->
                if (col.lowercaseChar() == xmas.first()) {
                    count += search(rowIdx, colIdx, map)
                }
            }
        }
        count
    }

    private fun search(rowIdx: Int, colIdx: Int, map: List<CharArray>): Int {
        val depth = 1

        var count = 0
        if (map.getOrNull(rowIdx - 1, colIdx) == xmas[depth]) {
            count += searchUp(rowIdx - 1, colIdx, map, depth).toInt()
        }
        if (map.getOrNull(rowIdx, colIdx - 1) == xmas[depth]) {
            count += searchLeft(rowIdx, colIdx - 1, map, depth).toInt()
        }
        if (map.getOrNull(rowIdx + 1, colIdx) == xmas[depth]) {
            count += searchDown(rowIdx + 1, colIdx, map, depth).toInt()
        }
        if (map.getOrNull(rowIdx, colIdx + 1) == xmas[depth]) {
            count += searchRight(rowIdx, colIdx + 1, map, depth).toInt()
        }
        if (map.getOrNull(rowIdx - 1, colIdx - 1) == xmas[depth]) {
            count += searchDiagUpLeft(rowIdx - 1, colIdx - 1, map, depth).toInt()
        }
        if (map.getOrNull(rowIdx - 1, colIdx + 1) == xmas[depth]) {
            count += searchDiagUpRight(rowIdx - 1, colIdx + 1, map, depth).toInt()
        }
        if (map.getOrNull(rowIdx + 1, colIdx + 1) == xmas[depth]) {
            count += searchDiagDownRight(rowIdx + 1, colIdx + 1, map, depth).toInt()
        }
        if (map.getOrNull(rowIdx + 1, colIdx - 1) == xmas[depth]) {
            count += searchDiagDownLeft(rowIdx + 1, colIdx - 1, map, depth).toInt()
        }
        return count
    }

    private fun Boolean?.toInt() = if (this == true) 1 else 0

    private fun searchUp(rowIdx: Int, colIdx: Int, map: List<CharArray>, depth: Int): Boolean {
        if (depth > 3) {
            return false
        } else if (map.getOrNull(rowIdx, colIdx) == xmas.last() && depth == 3) {
            return true
        }

        val nextChar = xmas[depth].lowercaseChar()
        return if (map.getOrNull(rowIdx, colIdx) != nextChar) {
            false
        } else {
            searchUp(rowIdx - 1, colIdx, map, depth + 1)
        }
    }
    private fun searchDown(rowIdx: Int, colIdx: Int, map: List<CharArray>, depth: Int): Boolean {
        if (depth > 3) {
            return false
        } else if (map.getOrNull(rowIdx, colIdx) == xmas.last() && depth == 3) {
            return true
        }

        val nextChar = xmas[depth].lowercaseChar()
        return if (map.getOrNull(rowIdx, colIdx) != nextChar) {
            false
        } else {
            searchDown(rowIdx + 1, colIdx, map, depth + 1)
        }
    }
    private fun searchLeft(rowIdx: Int, colIdx: Int, map: List<CharArray>, depth: Int): Boolean {
        if (depth > 3) {
            return false
        } else if (map.getOrNull(rowIdx, colIdx) == xmas.last() && depth == 3) {
            return true
        }

        val nextChar = xmas[depth].lowercaseChar()
        return if (map.getOrNull(rowIdx, colIdx) != nextChar) {
            false
        } else {
            searchLeft(rowIdx, colIdx - 1, map, depth + 1)
        }
    }
    private fun searchRight(rowIdx: Int, colIdx: Int, map: List<CharArray>, depth: Int): Boolean {
        if (depth > 3) {
            return false
        } else if (map.getOrNull(rowIdx, colIdx) == xmas.last() && depth == 3) {
            return true
        }

        val nextChar = xmas[depth].lowercaseChar()
        return if (map.getOrNull(rowIdx, colIdx) != nextChar) {
            false
        } else {
            searchRight(rowIdx, colIdx + 1, map, depth + 1)
        }
    }
    private fun searchDiagUpRight(rowIdx: Int, colIdx: Int, map: List<CharArray>, depth: Int): Boolean {
        if (depth > 3) {
            return false
        } else if (map.getOrNull(rowIdx, colIdx) == xmas.last() && depth == 3) {
            return true
        }

        val nextChar = xmas[depth].lowercaseChar()
        return if (map.getOrNull(rowIdx, colIdx) != nextChar) {
            false
        } else {
            searchDiagUpRight(rowIdx - 1, colIdx + 1, map, depth + 1)
        }
    }
    private fun searchDiagUpLeft(rowIdx: Int, colIdx: Int, map: List<CharArray>, depth: Int): Boolean {
        if (depth > 3) {
            return false
        } else if (map.getOrNull(rowIdx, colIdx) == xmas.last() && depth == 3) {
            return true
        }

        val nextChar = xmas[depth].lowercaseChar()
        return if (map.getOrNull(rowIdx, colIdx) != nextChar) {
            false
        } else {
            searchDiagUpLeft(rowIdx - 1, colIdx - 1, map, depth + 1)
        }
    }
    private fun searchDiagDownRight(rowIdx: Int, colIdx: Int, map: List<CharArray>, depth: Int): Boolean {
        if (depth > 3) {
            return false
        } else if (map.getOrNull(rowIdx, colIdx) == xmas.last() && depth == 3) {
            return true
        }

        val nextChar = xmas[depth].lowercaseChar()
        return if (map.getOrNull(rowIdx, colIdx) != nextChar) {
            false
        } else {
            searchDiagDownRight(rowIdx + 1, colIdx + 1, map, depth + 1)
        }
    }
    private fun searchDiagDownLeft(rowIdx: Int, colIdx: Int, map: List<CharArray>, depth: Int): Boolean {
        if (depth > 3) {
            return false
        } else if (map.getOrNull(rowIdx, colIdx) == xmas.last() && depth == 3) {
            return true
        }

        val nextChar = xmas[depth].lowercaseChar()
        return if (map.getOrNull(rowIdx, colIdx) != nextChar) {
            false
        } else {
            searchDiagDownLeft(rowIdx + 1, colIdx - 1, map, depth + 1)
        }
    }


    fun List<CharArray>.getOrNull(row: Int, col: Int) = this.getOrNull(row)?.getOrNull(col)?.lowercaseChar()
}
