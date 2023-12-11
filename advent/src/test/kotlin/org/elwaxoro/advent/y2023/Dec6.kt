package org.elwaxoro.advent.y2023

import org.elwaxoro.advent.PuzzleDayTester
import kotlin.math.roundToInt

class Dec6 : PuzzleDayTester(6, 2023) {
    override fun part1() = loadRaces(1).doTheMath()

    override fun part2() = loadRaces().doTheMath()

    fun List<Race>.doTheMath() = map {
        // formula is as follows: distance < Time*x - x^2 => x^2 - Time*x + distance + 0.1 = 0, where x is the seconds held
        // add 0.1 to distance bc quadratic formula will give solve for = to distance and we want numbers greater than distance
        // we dont wanna tie
        val (max, min) = getRoots(1.0, -1 * it.time, it.distance + 0.1).let { (root1, root2) ->
            if (root1 > root2) Math.floor(root1).roundToInt() to Math.ceil(root2).roundToInt()
            else Math.floor(root2).roundToInt() to Math.ceil(root1).roundToInt()
        }
        min .. max
    }.fold(1) { acc, next ->
        acc * next.count()
    }

    fun loadRaces(testNum: Int? = null): List<Race> {
        val (times, distances) = load(testNum).toList().map { it.split(" ") }
        return (times zip distances).map { Race(it.first.toDouble(), it.second.toDouble()) }
    }
    data class Race(val time: Double, val distance: Double)

    //ax2 + bx + c = 0
    fun getRoots(a: Double, b: Double, c: Double): Pair<Double, Double> {
        val root1: Double
        val root2: Double

        val determinant = b * b - 4.0 * a * c

        // condition for real and different roots
        if (determinant > 0) {
            root1 = (-b + Math.sqrt(determinant)) / (2 * a)
            root2 = (-b - Math.sqrt(determinant)) / (2 * a)
        }
        // Condition for real and equal roots
        else if (determinant == 0.0) {
            root2 = -b / (2 * a)
            root1 = root2
        }
        // If roots are not real
        else {
            throw IllegalStateException("roots are not real")
        }

        return root1 to root2
    }
}
