package org.elwaxoro.advent.y2022

import org.elwaxoro.advent.PuzzleDayTester

class Dec01: PuzzleDayTester(1, 2022) {

    override fun part1(): Any = load(delimiter = "\n\n").map { it.split("\n") }.map { elf -> elf.map { it.toInt() } }.maxOf { it.sum() }

}