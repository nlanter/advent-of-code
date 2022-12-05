package org.elwaxoro.advent.y2022

import org.elwaxoro.advent.PuzzleDayTester
import java.util.*

class Dec05 : PuzzleDayTester(5, 2022) {
    private val distanceBetweenLetters = 4

    override fun part1(): Any = loadData().also { (stacks, moves) ->
        moveBoxes(moves, stacks)
    }.let { (stacks, _) ->
        stacks.getFirstRow()
    }

    override fun part2(): Any = loadData().also { (stacks, moves) ->
        moveBoxesPart2(moves, stacks)
    }.let { (stacks, _) ->
        stacks.getFirstRow()
    }

    private fun moveBoxesPart2(
        moves: List<Move>,
        stacks: MutableMap<Int, Stack<Char>>
    ) {
        moves.forEach { move: Move ->
            val tmpList = mutableListOf<Char>()
            repeat(move.count) {
                stacks[move.from]?.takeIf { it.isNotEmpty() }?.pop()?.let {
                    tmpList.add(it)
                }
            }

            tmpList.reversed().forEach {
                stacks[move.to]?.push(it)
            }

        }
    }

    private fun loadData(): Pair<MutableMap<Int, Stack<Char>>, List<Move>> {
        val input = load()
        val stacks = getInitialState(input)

        val moves: List<Move> = getMovesList(input)
        return Pair(stacks, moves)
    }

    private fun moveBoxes(
        moves: List<Move>,
        stacks: MutableMap<Int, Stack<Char>>
    ) {
        moves.forEach { move: Move ->
            repeat(move.count) {
                val popped = stacks[move.from]?.takeIf { it.isNotEmpty() }?.pop()
                popped?.let { stacks[move.to]?.push(popped) }
            }
        }
    }

    private fun getMovesList(input: List<String>): List<Move> {
        val indOfFirstMove = input.indexOf(input.first { it.contains("from") })
        val moves: MutableList<Move> = mutableListOf()

        for (row in indOfFirstMove until input.size) {
            val reggy = "move (\\d{1,2}) from (\\d{1,2}) to (\\d{1,2})".toRegex()
            val matcher = reggy.matchEntire(input[row])
            matcher?.let {
                moves += Move(count = it.groups[1].toInt(), from = it.groups[2].toInt(), to = it.groups[3].toInt())
            }
        }

        return moves
    }

    data class Move(val count: Int, val from: Int, val to: Int) {}

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
    private fun MutableMap<Int, Stack<Char>>.getFirstRow() = this.map { it.value.pop() }.joinToString("")

}