package org.elwaxoro.advent.y2023

import org.elwaxoro.advent.PuzzleDayTester

class Dec03 : PuzzleDayTester(3, 2023) {
    //522726
    override fun part1(): Any = load(testNum = 1).let {
        it to it.mapToPartNumbers()
    }.let { (originalTable, partNumbers) ->
        // populate part number values and check if it's valid
        partNumbers.onEach { part ->
            originalTable[part.row].substring(part.cols.first, part.cols.last + 1).toInt().also { part.value = it }
            originalTable.checkPartNumber(part)
        }
    }.filter { it.isValid }
    .sumOf { it.value }

    private fun List<String>.checkPartNumber(partNum: PartNumber): Boolean = let { strs ->
        val maxColIdx = strs[0].length - 1
        val maxRowIdx = strs.size - 1
        val cols = partNum.cols
        val row = partNum.row
        // check neighbors
        fun String.getNeighborsForRow() = substring((cols.first - 1).coerceAtLeast(0), (cols.last + 2).coerceAtMost(maxColIdx))
        val aboveNeighbors = strs[(row - 1).coerceAtLeast(0)].getNeighborsForRow()
        val belowNeighbors = strs[(row + 1).coerceAtMost(maxRowIdx)].getNeighborsForRow()
        val middleNeighbors = strs[row].getNeighborsForRow()
        val isValidPart = aboveNeighbors.plus(belowNeighbors).plus(middleNeighbors).any { !it.isDigit() && it != '.' }
        partNum.isValid = isValidPart
        return isValidPart
    }

    private fun List<String>.mapToPartNumbers() = flatMapIndexed { rowIdx, row ->
        val parts = mutableListOf<PartNumber>()
        var partNumber: PartNumber? = null
        row.onEachIndexed { colIdx, char ->
            if (!char.isDigit()) {
                partNumber?.also { parts.add(it) }; partNumber = null
            } else if (partNumber != null) {
                partNumber!!.cols = IntRange(partNumber!!.cols.first, colIdx)
            } else {
                partNumber = PartNumber(rowIdx, cols = IntRange(colIdx, colIdx))
            }

            if (colIdx == row.length - 1 && partNumber != null) {
                // end of line, make sure to add part
                parts.add(partNumber!!)
            }
        }
        parts
    }

    open class PartNumber(val row: Int, var cols: IntRange, var value: Int = -1, var isValid: Boolean = false) {
        override fun toString(): String {
            return "$row, ${cols.first} .. ${cols.last}"
        }
    }

}
