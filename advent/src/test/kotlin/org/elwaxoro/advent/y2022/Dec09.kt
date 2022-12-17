package org.elwaxoro.advent.y2022

import org.elwaxoro.advent.Coord
import org.elwaxoro.advent.Dir
import org.elwaxoro.advent.PuzzleDayTester
import org.elwaxoro.advent.printify

class Dec09 : PuzzleDayTester(9, 2022) {

    override fun part1(): Any {
        val moves = getMoves()
        val tails = mutableListOf<Node>().apply { repeat(10) {add(Node())} }
        moves.onEach { (dir, count) ->
            repeat(count) {
                tails.onEachIndexed { idx, currTail ->
                    if (idx > 0) {
                        val currHead = tails[idx - 1]
                        if (currTail.isTailNotTouchingHead(currHead)) {
                            currTail.moveTo(currHead)
                            if(idx == tails.size - 1) {println("moving tail ${currTail.currCoord} on $dir $count") }
                        }
                        currTail.trackPosition()
                    } else {
                        tails[idx].moveTo(dir)
                        println("moving head ${currTail.currCoord} on $dir $count")
                    }
                }
            }
            println(tails.last().positions.printify(invert = true))

        }


        println(tails.last().positions.printify(invert = true))
        return tails.last().positions.size
    }

    class Node(var currCoord: Coord = Coord(0, 0), var prevCoord: Coord = currCoord, val positions: MutableSet<Coord> = mutableSetOf(currCoord)) {
        fun moveTo(next: Node) {
            prevCoord = currCoord
            currCoord = next.prevCoord
        }

        fun trackPosition() = positions.add(Coord(currCoord.x, currCoord.y))
        fun isTailNotTouchingHead(head: Node) = currCoord.neighbors9().none { it.contains(head.currCoord) }

        fun moveTo(dir: Dir) { prevCoord = currCoord; currCoord = currCoord.move(dir) }
    }

    private fun getMoves() = load().map { it.split(" ").let { Dir.fromUDLR(it[0].first()) to it[1].toInt() } }

}