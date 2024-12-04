package org.elwaxoro.advent

import java.nio.file.Path
import java.nio.file.Paths
import kotlin.test.Test
import kotlin.test.assertNotEquals

/**
 * Extend PuzzleDayTester with your puzzle for the day!
 * Puzzles generally come in two parts, so extend part1() and part2() as you go
 * Run your class as a unit test to get that sweet sweet output
 */
abstract class PuzzleDayTester(val day: Int, val year: Int) {

    /**
     * Part1: Extend me!
     */
    open fun part1(): Any = "TODO"

    /**
     * Part2: Extend me!
     */
    open fun part2(): Any = "TODO"

    /**
     * Default loader, gets the input file and returns a list of strings
     */
    open fun load(testNum: Int? = null, delimiter: String = "\n"): List<String> = loadSplit(getPath(testNum), delimiter)

    open fun loadToString(testNum: Int? = null): String = loadText(getPath(testNum))

    /**
     * Default loader, gets the input file and returns a list of integers
     */
    open fun loadToInt(testNum: Int? = null, delimiter: String = "\n"): List<Int> = load(testNum, delimiter).map { it.toInt() }

    /**
     * Default loader, gets the input file and returns a list of longs
     */
    open fun loadToLong(testNum: Int? = null, delimiter: String = "\n"): List<Long> = load(testNum, delimiter).map { it.toLong() }

    /**
     * Default loader: Loads whatever the path points to into a single string
     * Generally just call "load" instead, but this can be useful for loading multi-line JSON or something
     */
    fun loadText(path: Path): String = path.toUri().toURL().readText()

    /**
     * Helper: gets the absolute path for the given puzzle day
     */
    fun getPath(testNum: Int? = null): Path = Paths.get("src/test/resources/${getDayFilename(day, year, testNum)}").toAbsolutePath()

    /**
     * Unit test for part one of puzzles
     * Shouldn't need to touch this
     */
    @Test
    open fun testPart1() = testPuzzle("$year-$day Puzzle 1", ::part1)

    /**
     * Unit test for part two of puzzles
     * Shouldn't need to touch this either!
     */
    @Test
    open fun testPart2() = testPuzzle("$year-$day Puzzle 2", ::part2)

    /**
     * Runs the test, prints its output and how long it took
     */
    private fun testPuzzle(name: String, puzzle: () -> Any) {
        val start = System.currentTimeMillis()
        puzzle().let {
            println("$name: $it [${System.currentTimeMillis()-start}ms]")
            assertNotEquals("TODO", it, "$name not implemented")
        }
    }

    /**
     * Helper: Converts day/year and optional test number into the expected resources path and filename
     */
    private fun getDayFilename(day: Int, year: Int, testNum: Int? = null): String =
        "$year/Dec${if (day < 10) "0$day" else day}${if (testNum != null) "-test-$testNum" else ""}.txt"

    /**
     * Helper: Loads the path into a list of strings, based on provided delimiter or newline
     * Generally just call "load" instead
     */
    private fun loadSplit(path: Path, delimiter: String = "\n"): List<String> = loadText(path).split(delimiter)
}
