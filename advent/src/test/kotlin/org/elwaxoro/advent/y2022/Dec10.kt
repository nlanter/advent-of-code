package org.elwaxoro.advent.y2022

import org.elwaxoro.advent.PuzzleDayTester
import java.util.*

class Dec10 : PuzzleDayTester(10, 2022) {

    override fun part1(): Any {
        val instr = loadInstructions()
        var cycle = 1
        var register = 1
        var strength = 0
        val remainingCycles = Stack <Pair<Instruction, Int?>>()

        while (instr.isNotEmpty() || remainingCycles.isNotEmpty()) {
            val currInstr = if(instr.isNotEmpty()) instr.pop() else null

            if (remainingCycles.isNotEmpty()) {
                register += remainingCycles.pop().second ?: 0
            }
//            println("cycle $cycle, register $register, currInstr $currInstr")

            if (currInstr?.first != Instruction.NOOP) {
                remainingCycles.push(currInstr)
//                println("detected ${currInstr?.first}, skipping cycle ${cycle + 1}")
                cycle++
            }

            strength += checkStrength(cycle, register)
            cycle++
        }

        return "register: $register, strength: $strength"
    }

    private fun checkStrength(cycle: Int, register: Int): Int {
        return when {
            (cycle - 20) % 40 == 0 ->  register * cycle
            else -> 0
        }
    }


    fun loadInstructions() = load().map { instruction ->
        instruction.substringBefore(" ").toInstruction() to instruction.substringAfter(" ").toIntOrNull()
    }.reversed().toCollection(Stack())

    fun String.toInstruction(): Instruction = Instruction.valueOf(this.toUpperCase())


    enum class Instruction(val numCycles: Int) {
        NOOP(1),
        ADDX(2);
    }
}