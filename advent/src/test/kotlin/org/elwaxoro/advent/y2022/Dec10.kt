package org.elwaxoro.advent.y2022

import org.elwaxoro.advent.PuzzleDayTester
import java.util.*

class Dec10 : PuzzleDayTester(10, 2022) {

    override fun part1(): Any {
        val instr = loadInstructions()
        var counter = 1
        var register = 1
        var strength = 0
        val remainingCycles = Stack <Pair<Instruction, Int?>>()

        while (instr.isNotEmpty() || remainingCycles.isNotEmpty()) {
            val currInstr = if(instr.isNotEmpty()) instr.pop() else null


            if(remainingCycles.isNotEmpty()) {
                val ins = remainingCycles.pop()
                println("Executing instruction $ins")
                register += ins.second ?: 0
            }
            println("cycle $counter, register $register, currInstr $currInstr")

            when {
                counter == 20 -> strength += register * 20
//                (counter - 20 % 40 == 0) -> strength += register * counter - 20
                counter == 60 -> strength += register * 60
                counter == 100 -> strength += register * 100
                counter == 140 -> strength += register * 140
                counter == 180 -> strength += register * 180
                counter == 220 -> strength += register * 220

            }
            if (counter % 20 == 0) {
                println("strength now $strength at cycle $counter")
            }

            if (currInstr?.first != Instruction.NOOP) {
                remainingCycles.push(currInstr)
                counter++
            }
            counter++
        }

        return "register: $register, strength: $strength"
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