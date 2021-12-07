package org.elwaxoro.advent

fun ByteArray.toHexString() = joinToString("") { (0xFF and it.toInt()).toString(16).padStart(2, '0') }

/**
 * Split a string into a list of integers
 * Ex: "01234" becomes [0, 1, 2, 3, 4]
 */
fun String.splitToInt(): List<Int> = map(Character::getNumericValue)

/**
 * Replace matching values
 */
fun List<Int>.replace(oldInt: Int, newInt: Int): List<Int> = map { it.takeUnless { it == oldInt } ?: newInt }

/**
 * Takes a list of 1s and 0s and turns it into a single integer
 */
fun List<Int>.toBinaryInt(): Int = joinToString("").toInt(2)

/**
 * 0s become 1s, 1s become 0s
 */
fun List<Int>.bitFlip(): List<Int> = map {
    when (it) {
        1 -> 0
        0 -> 1
        else -> throw IllegalStateException("can't bit flip $it")
    }
}

/**
 * Rotate a matrix by swapping rows for columns
 */
fun <T> MutableList<MutableList<T>>.rowColSwap(): MutableList<MutableList<T>> =
    MutableList(size) { MutableList(size) { this[0][0] } }.also { translate ->
        forEachIndexed { rowIdx, row ->
            row.forEachIndexed { colIdx, value ->
                translate[colIdx][rowIdx] = value
            }
        }
    }

/**
 * Based on https://stackoverflow.com/questions/9562605/in-kotlin-can-i-create-a-range-that-counts-backwards
 * Kotlin ranges don't support positive or negative directions at the same time
 */
fun Int.toward(to: Int): IntProgression = IntProgression.fromClosedRange(this, to, 1.takeIf { this <= to } ?: -1)

fun IntProgression.padTo(newSize: Int): List<Int> = toList().padTo(newSize)

fun <T> List<T>.padTo(newSize: Int): List<T> = takeIf { size >= newSize } ?: plus(List(newSize - size) { last() })

fun List<Int>.median(): Double = sorted().let {
    if (size % 2 == 0) {
        (it[size / 2] + it[size / 2 - 1]) / 2.0
    } else {
        it[size / 2].toDouble()
    }
}
