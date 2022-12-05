package org.elwaxoro.advent.y2022

import org.elwaxoro.advent.PuzzleDayTester
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class Dec05 : PuzzleDayTester(5, 2022) {
    private val distanceBetweenLetters = 4

    override fun part1(): Any {
        val input = load()
        val stacks = getInitialState(input)

        val indOfFirstMove = input.indexOf(input.first { it.contains("from") })

        val moves: List<Move> = getMovesList(indOfFirstMove, input)

        // move boxes
        moveBoxes(moves, stacks)


        //grab top from each stack
        var output = ""
        stacks.onEach { output += it.value.pop() }

        return output
    }

    private fun moveBoxes(
        moves: List<Move>,
        stacks: MutableMap<Int, Stack<Char>>
    ) {
        moves.forEachIndexed { ind, move: Move ->
            for (i in 0 until move.count) {
                val popped = stacks[move.from]?.takeIf { it.isNotEmpty() }?.pop()
                popped?.let { stacks[move.to]?.push(popped) }
            }
        }
    }

    private fun getMovesList(indOfFirstMove: Int, input: List<String>): List<Move> {
        var moves: List<Move> = mutableListOf()
        for (row in indOfFirstMove until input.size) {
            val reggy = "move (\\d{1,2}) from (\\d{1,2}) to (\\d{1,2})".toRegex()
            val matcher = reggy.matchEntire(input[row])
            matcher?.let {
                moves += Move(count = it.groups[1].toInt(), from = it.groups[2].toInt(), to = it.groups[3].toInt())
            }
        }

        return moves
    }

    data class Move(val count: Int, val from: Int, val to: Int) {

    }

    private fun getInitialState(input: List<String>): MutableMap<Int, Stack<Char>> {
        val rowContainingColumnNumbers = input.first { it[1].isDigit() }
        val initialState = input.take(input.indexOf(rowContainingColumnNumbers))
        val stackMap = mutableMapOf<Int, Stack<Char>>()

        // populate initial stacks
        for (rowInd in initialState.size - 1 downTo 0) {
            for (colInd in 1..initialState[0].length step distanceBetweenLetters) {
                initialState[rowInd][colInd].takeIf { it.isLetter() }?.also {
                    val currentStack = stackMap[(colInd / distanceBetweenLetters) + 1]
                    if (null == currentStack) stackMap[(colInd / distanceBetweenLetters) + 1] = Stack()
                    stackMap[(colInd / distanceBetweenLetters) + 1]?.push(it)
                }
            }
        }


        return stackMap
    }

    private fun MatchGroup?.toInt() = this?.value.toString().toInt()

}