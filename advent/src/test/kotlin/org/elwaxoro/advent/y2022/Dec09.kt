package org.elwaxoro.advent.y2022

import org.elwaxoro.advent.Coord
import org.elwaxoro.advent.Dir
import org.elwaxoro.advent.PuzzleDayTester
import org.elwaxoro.advent.printify

class Dec09 : PuzzleDayTester(9, 2022) {

    override fun part1(): Any {
        val moves = getMoves()

        val head = Node()
        val prevHeadC = Node()
        val tail = Node()
        moves.onEach { (dir, count) ->
            repeat(count) {
                prevHeadC.moveTo(head)
                head.moveTo(dir)

                if (tail.isTailNotTouchingHead(head)) {
                    tail.moveTo(prevHeadC)
                }
                tail.trackPosition()
            }

        }


        println(tail.positions.printify(invert = true))
        return tail.positions.size
    }

    class Node(var currCoord: Coord = Coord(0, 0), val positions: MutableSet<Coord> = mutableSetOf(currCoord)) {
        fun moveTo(next: Node) {
            currCoord = next.currCoord
        }

        fun trackPosition() = positions.add(Coord(currCoord.x, currCoord.y))
        fun isTailNotTouchingHead(head: Node) = currCoord.neighbors9().none { it.contains(head.currCoord) }

        fun moveTo(dir: Dir) { currCoord = currCoord.move(dir) }
    }

    private fun getMoves() = load().map { it.split(" ").let { Dir.fromUDLR(it[0].first()) to it[1].toInt() } }

}