package org.elwaxoro.advent.y2022

import org.elwaxoro.advent.Coord
import org.elwaxoro.advent.Dir
import org.elwaxoro.advent.PuzzleDayTester
import org.elwaxoro.advent.printify

class Dec09 : PuzzleDayTester(9, 2022) {

    override fun part1(): Any {
        val moves = getMoves()

        var head = Coord(0, 0)
        var prevHead = Coord(0, 0)
        var tail = Coord(0, 0)
        val tailPositions = mutableSetOf<Coord>()
        moves.onEach { (dir, count) ->
            repeat(count) {
                prevHead = head
                head = head.move(dir)

                if (tail.neighbors9().none { it.contains(head) }) {
                    tail = prevHead
                }
                tailPositions.add(Coord(tail.x, tail.y))
            }

        }


        println(tailPositions.printify(invert = true))
        return tailPositions.size
    }


    private fun getMoves() = load().map { it.split(" ").let { Dir.fromUDLR(it[0].first()) to it[1].toInt() } }

}