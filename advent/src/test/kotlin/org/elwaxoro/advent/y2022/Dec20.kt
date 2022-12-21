package org.elwaxoro.advent.y2022

import org.elwaxoro.advent.PuzzleDayTester

class Dec20 : PuzzleDayTester(20, 2022) { //11073
    data class Thingy(
        val value: Int,
        val ogIdx: Int
    ) {} // ogIdx to preserve uniqueness when finding current idx of value

    override fun part1(): Any = CircularList(loadToInt()).run()
    
    data class CircularList(val originalList: List<Int>) {
        private val originalthingyList: MutableList<Thingy> =
            originalList.mapIndexed { idx, value -> Thingy(value, idx) }.toMutableList()
        private val thingyList: MutableList<Thingy> = originalthingyList.toMutableList()

        fun run(): Int {
            originalthingyList.onEach { move(thingyList, it) }
            return listOf(1000, 2000, 3000)
                .sumOf {
                    thingyList[(thingyList.indexOfFirst { it.value == 0 } + it) % thingyList.size].value
                }
        }

        fun move(list: MutableList<Thingy>, thingy: Thingy) {
            val numToMove = thingy.value
            if (numToMove == 0) {
                println("0 does not move"); return
            }
            val idxOfNum = list.indexOf(thingy)
            val targetIdx = idxOfNum + numToMove
            val target = when {
                targetIdx > list.lastIndex -> targetIdx % list.lastIndex // goes past end, mod to remove times wrapped around
                targetIdx < 0 -> (targetIdx % list.lastIndex) + list.lastIndex // goes past beginning
                else -> targetIdx
            }

//        println("moving $numToMove from $idxOfNum to $target")
            swap(list, idxOfNum, target)
        }

        private fun swap(list: MutableList<Thingy>, idxOfNum: Int, targetIdx: Int) {
            val v = list.removeAt(idxOfNum)
            list.add(targetIdx, v)
        }
    }
}
