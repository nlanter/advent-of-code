package org.elwaxoro.advent.y2022

import org.elwaxoro.advent.PuzzleDayTester
import kotlin.math.abs

class Dec20 : PuzzleDayTester(20, 2022) {

    override fun part1(): Any = loadToInt().let {
        val new = it.toMutableList()

        it to new
    }.let { (original, mutable) ->
        original.onEach { ogNum ->
            println("Moving $ogNum from ${mutable.indexOf(ogNum)}")
            move(mutable, ogNum)
            println(mutable)
        }
            mutable
    }.let {
            mutable -> listOf(1000, 2000, 3000)
        .sumOf {
            val idx = if (mutable.size > it) it + 1 else it % mutable.size - mutable.indexOf(0) + 1
            mutable[idx]
        }
    }

    fun move(list: MutableList<Int>, numToMove: Int) {
        if (numToMove == 0) {println("0 does not move"); return}

        if (numToMove < 0) moveReversed(list, numToMove) else moveForward(list, numToMove)
    }

    private fun moveForward(list: MutableList<Int>, numToMove: Int) {
        val idxOfNum = list.indexOfFirst { it == numToMove }
        if (idxOfNum + numToMove == list.size - 1) {
            //move to beginning
            println("edge case on moving right")
            moveLeft(idxOfNum, idxOfNum * -1, list)
        } else if (idxOfNum + numToMove > list.size - 1) { // 1 + 5 = 6 > 5
            // we will end up wrapping around to the right so move left
            println("will go past end if proceeding forward")
            val spacesSpentGoingToEnd = list.size - 1 - idxOfNum
            val targetIdx = numToMove - spacesSpentGoingToEnd
            val numTimesToMoveLeft = idxOfNum - targetIdx
            println("moving left from $idxOfNum $numTimesToMoveLeft times")
            moveLeft(idxOfNum, numTimesToMoveLeft * -1, list)
        }else {
            moveRight(idxOfNum, numToMove, list)
        }
    }

    private fun moveReversed(list: MutableList<Int>, numToMove: Int) {
        val idxOfNum = list.indexOfFirst { it == numToMove }
        //        5 from [1, -5, 3, 4, 7, 6]
        // [1, -5, 3, 4, 7, 6]
        if (idxOfNum + numToMove == 0) {
            println("edge case on moving left")
            //move to end
            val timesToMoveToReachEnd = list.size - idxOfNum - 1
            moveRight(idxOfNum, timesToMoveToReachEnd, list)
        } else if (abs(numToMove) > idxOfNum) { // 1 + 5 = 6 > 5
            // we will end up wrapping around to the left so move right
            moveRight(idxOfNum, list.size - abs(numToMove) - 1, list)
        } else {
            moveLeft(idxOfNum, numToMove, list)
        }
    }

    private fun moveRight(idxOfNum: Int, timesToMove: Int, list: MutableList<Int>) {
        println("moving right from $idxOfNum ${(idxOfNum + timesToMove) - idxOfNum} times")
        for (i in idxOfNum until idxOfNum + timesToMove) {
            val tmp = list[i + 1]
            list[i + 1] = list[i]
            list[i] = tmp
        }
    }

    private fun moveLeft(idxOfNum: Int, numToMove: Int, list: MutableList<Int>) {
        for (i in idxOfNum downTo idxOfNum + numToMove + 1) { //numToMove negative
            val tmp = list[i - 1]
            list[i - 1] = list[i]
            list[i] = tmp
        }
    }
}