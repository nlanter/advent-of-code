package org.elwaxoro.advent

/*
 * Extensions for messing with row/column based list of lists
 * Ex: [ [rowAcol0, rowAcol1, rowAcol2], [rowBcol0, rowBcol1, rowBcol2]]
 * IMPORTANT! if thinking of the list of lists as a coordinate grid, row = y and col = x
 */

/**
 * Rotate a matrix by swapping rows for columns
 */
fun <T> List<List<T>>.rowColSwap(): MutableList<MutableList<T>> =
    MutableList(size) { MutableList(size) { this[0][0] } }.also { translate ->
        forEachIndexed { rowIdx, row ->
            row.forEachIndexed { colIdx, value ->
                translate[colIdx][rowIdx] = value
            }
        }
    }

/**
 * Get the item at the given indexes, returning null if out of bounds of the lists
 * IMPORTANT! if thinking of the list of lists as a coordinate grid, row = y and col = x
 */
fun <T> List<List<T>>.getOrNull(row: Int, col: Int): T? =
    if (row < 0 || row >= size || col < 0 || col >= this[row].size) {
        null
    } else {
        this[row][col]
    }

/**
 * Return items in list of lists above, below, left, right of given indexes
 * Does NOT include the center item
 * Does NOT return diagonals
 * Excludes out of bounds coordinates
 * IMPORTANT! if thinking of the list of lists as a coordinate grid, row = y and col = x
 */
fun <T> List<List<T>>.neighbors(row: Int, col: Int): List<T> =
    listOfNotNull(
        getOrNull(row - 1, col),
        getOrNull(row + 1, col),
        getOrNull(row, col - 1),
        getOrNull(row, col + 1)
    )
