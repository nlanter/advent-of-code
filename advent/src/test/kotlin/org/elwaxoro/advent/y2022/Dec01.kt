package org.elwaxoro.advent.y2022

import org.elwaxoro.advent.PuzzleDayTester

class Dec01: PuzzleDayTester(1, 2022) {

    override fun part1(): Any = load(delimiter = "\n\n").map { elfValues ->  elfValues.split("\n").map { calories -> calories.toInt() } }.maxOf { it.sum() }

}