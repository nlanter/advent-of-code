package org.elwaxoro.advent.y2022

import org.elwaxoro.advent.PuzzleDayTester

class Dec20 : PuzzleDayTester(20, 2022) {
    data class Thingy(
        val value: Long,
        val ogIdx: Int
    ) {} // ogIdx to preserve uniqueness when finding current idx of value

    val data = loadToLong()
    override fun part1(): Any = CircularList(data).run() //11073
    override fun part2(): Any = CircularList(data, decryptionKey = 811589153).run(10)

    data class CircularList(val originalList: List<Long>, val decryptionKey: Long = 1) {
        private val originalThingyList: MutableList<Thingy> = originalList.mapIndexed { idx, value -> Thingy(value * decryptionKey, idx) }.toMutableList()
        private val thingyList: MutableList<Thingy> = originalThingyList.toMutableList()

        fun run(mixCount: Long = 1): Long {
            println("${Integer.MAX_VALUE}")
            for (i in 0 until mixCount) {
                originalThingyList.onEach {
                    move(thingyList, it)
                }
                println("$i mixed ${thingyList.map { it.value }}")
            }
            return listOf(1000, 2000, 3000)
                .sumOf {
                    thingyList[(thingyList.indexOfFirst { it.value == 0L } + it) % thingyList.size].value
                }
        }

        val endIdx: Long = thingyList.lastIndex.toLong()

        fun move(list: MutableList<Thingy>, thingy: Thingy) {
            val numToMove = thingy.value
            if (numToMove == 0L) {
                println("0 does not move"); return
            }
            val idxOfNum = list.indexOf(thingy)
            val targetIdx = idxOfNum + numToMove
            val target = when {
                targetIdx > endIdx -> targetIdx % endIdx // goes past end, mod to remove times wrapped around
                targetIdx < 0 -> (targetIdx % endIdx) + endIdx // goes past beginning
                else -> targetIdx
            }

//        println("moving $numToMove from $idxOfNum to $target")
            swap(list, idxOfNum, target.toInt())
        }

        private fun swap(list: MutableList<Thingy>, idxOfNum: Int, targetIdx: Int) {
            val v = list.removeAt(idxOfNum)
            list.add(targetIdx, v)
        }
    }
}
