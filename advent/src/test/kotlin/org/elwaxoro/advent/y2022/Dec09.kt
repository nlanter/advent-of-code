package org.elwaxoro.advent.y2022

import org.elwaxoro.advent.Coord
import org.elwaxoro.advent.Dir
import org.elwaxoro.advent.PuzzleDayTester
import org.elwaxoro.advent.printify

class Dec09 : PuzzleDayTester(9, 2022) {

    override fun part1(): Any {
        val moves = getMoves()

        var head = Coord(0, 0)
        var tail = Coord(0, 0)
        var tailPositions = mutableSetOf<Coord>()
        moves.onEach { (dir, count) ->
            repeat(count) {
                head = head.move(dir)

//                tail = moveTail(tail, head)
                if (tail.neighbors9().none { it.contains(head) }) {
                    tailPositions.add(Coord(tail.x, tail.y))
                    tail = head
                }
            }

        }

        println(tailPositions.printify(invert = true))

        return tailPositions.size
    }


    private fun getMoves() = load().map { it.split(" ").let { Dir.fromUDLR(it[0].first()) to it[1].toInt() } }

}