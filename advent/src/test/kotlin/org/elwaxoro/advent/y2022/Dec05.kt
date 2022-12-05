package org.elwaxoro.advent.y2022

import org.elwaxoro.advent.PuzzleDayTester
import org.elwaxoro.advent.rowColSwap

/**
 * Day 5: Supply Stacks
 */
class Dec05 : PuzzleDayTester(5, 2022) {

    /**
     * Presumably, a CrateMover 9000
     */
    override fun part1(): Any = loader().let { (stacks, moves) ->
        moves.forEach { move ->
            val sourceStack = stacks[move[1]]!!
            val destStack = stacks[move[2]]!!
            val copyCount = move[0].toInt()
            val toMove = sourceStack.take(copyCount)
            stacks[move[1]] = sourceStack.drop(copyCount)
            stacks[move[2]] = toMove.reversed().plus(destStack) // CrateMover 9000 drops crates in reverse order onto the new stack
        }
        stacks.map { it.value.first() }.joinToString("")
    }// == "FWSHSPJWM"

    /**
     * Wait this isn't a CrateMover 9000 it's a CrateMover 9001
     * OH SHIT
     */
    override fun part2(): Any = loader().let { (stacks, moves) ->
        moves.forEach { move ->
            val sourceStack = stacks[move[1]]!!
            val destStack = stacks[move[2]]!!
            val copyCount = move[0].toInt()
            val toMove = sourceStack.take(copyCount)
            stacks[move[1]] = sourceStack.drop(copyCount)
            stacks[move[2]] = toMove.plus(destStack) // CrateMover 9001 drops crates in their original order
        }
        stacks.map { it.value.first() }.joinToString("")
    }// == "PWPWHGFZS"

    /**
     * The award for most annoying parser goes to...
     */
    private fun loader() = load(delimiter = "\n\n").let { (stacks, moves) ->

        //parse the "stacks" section of input
        val parsedStacks = stacks.split("\n").map {
            it.chunked(4).toMutableList() // mutable lists here so I can reuse rowColSwap
        }.toMutableList().rowColSwap().map { swapped -> // vertical stacks are now rows, last in row is the stack number
            // get rid of square braces, spaces, and empty slots
            swapped.map { it.replace("[", "").replace("]", "").trim() }.filterNot { it.isBlank() }
        }.filter { it.isNotEmpty() }.associate {
            // last item in list is the stack number, everything else is the stack contents
            // first in stack list is the top item
            it.last() to it.dropLast(1)
        }.toMutableMap()

        // parse the "instructions" section of input
        val parsedMoves = moves.split("\n").map { line ->
            // get rid of all the text, then split on whitespace
            line.replace("move ", "").replace("from ", "").replace("to ", "").split(" ")
        }
        parsedStacks to parsedMoves
    }
}
