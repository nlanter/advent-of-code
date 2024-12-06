package org.elwaxoro.advent.y2024

import org.elwaxoro.advent.Dir
import org.elwaxoro.advent.PuzzleDayTester
import org.elwaxoro.advent.Turn
import org.nlanter.advent.Grid

class Dec6 : PuzzleDayTester(6, 2024) {

    override fun part1(): Any = load().let {
        val grid = Grid(it, startChar = '^', initialDir = Dir.N)
        var moves = 0
        // step 1 move north to the top
        while (grid.nextMove()?.d != '#') {
            val visited = grid.nextMove()?.visited == true
            grid.move().also { if (!visited) moves++ }
        }

        // step 2, turn right and move in that direction
        while (grid.nextMove() != null) {
            if (grid.nextMove()?.d == '#') {
                grid.turn(Turn.R)
            }
            val visited = grid.nextMove()?.visited == true
            grid.move().also { if (!visited) moves++ }
        }

        moves + 1 // need to count the last spot
    }

}
