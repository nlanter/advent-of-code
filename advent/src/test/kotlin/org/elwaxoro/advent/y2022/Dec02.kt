package org.elwaxoro.advent.y2022

import org.elwaxoro.advent.PuzzleDayTester

class Dec02: PuzzleDayTester(2, 2022) {

    override fun part1(): Any {
        var opponentScore = 0
        var myScore = 0

        load().forEach {
            val (opp, me) = it.split(" ").let{ plays -> RPS.fromString(plays[0]) to RPS.fromString(plays[1])}
            when {
                opp.beats(me) -> opponentScore += winningScore
                me.beats(opp) -> myScore += winningScore
                else -> {opponentScore += drawScore; myScore += drawScore}
            }

            opponentScore += opp.playScore
            myScore += me.playScore
        }

        return myScore
    }

    enum class RPS(private val opponentPlay: String, private val myPlay: String, val playScore: Int) {
        ROCK("A", "X", 1),
        PAPER("B", "Y", 2),
        SCISSORS("C", "Z", 3),
        IGNORED("", "", 0);

        fun beats(other: RPS): Boolean {
            return when {
                this == ROCK && other == PAPER -> false
                this == ROCK && other == SCISSORS -> true
                this == PAPER && other == ROCK -> true
                this == PAPER && other == SCISSORS -> false
                this == SCISSORS && other == ROCK -> false
                this == SCISSORS && other == PAPER -> true
                else -> false
            }
        }

        companion object {
            fun fromString(str: String): RPS {
                return RPS.values().firstOrNull { it.opponentPlay == str || it.myPlay == str } ?: IGNORED
            }
        }
    }

    companion object {
        const val winningScore = 6
        const val drawScore = 3
    }

    override fun part2(): Any {
        var myScore = 0

        load().forEach {
            val (opp, winLoseOrDraw) = it.split(" ").let{ plays -> RPSV2.fromString(plays[0]) to WinLoseOrDraw.fromString(plays[1])}

            val score = getMyPlay(winLoseOrDraw, opp).playScore + winLoseOrDraw.playScore
            println("enemy played $opp, i played ${getMyPlay(winLoseOrDraw, opp)} and $winLoseOrDraw with a score of $score")
            myScore += score
        }

        return myScore
    }

    private fun getMyPlay(winLoseOrDraw: WinLoseOrDraw, rps: RPSV2): RPSV2 {
        return when (winLoseOrDraw) {
            WinLoseOrDraw.WIN -> rps.losesTo()
            WinLoseOrDraw.LOSE -> rps.beats()
            else -> rps
        }
    }

    enum class RPSV2(private val opponentPlay: String, val playScore: Int) {
        ROCK("A",  1),
        PAPER("B", 2),
        SCISSORS("C",  3),
        IGNORED("",  0);

        fun beats(): RPSV2 {
            return when {
                this == ROCK -> SCISSORS
                this == PAPER -> ROCK
                this == SCISSORS -> PAPER
                else -> IGNORED
            }
        }
        fun losesTo(): RPSV2 {
            return when {
                this == ROCK -> PAPER
                this == PAPER -> SCISSORS
                this == SCISSORS -> ROCK
                else -> IGNORED
            }
        }

        companion object {
            fun fromString(str: String): RPSV2 {
                return RPSV2.values().firstOrNull { it.opponentPlay == str } ?: IGNORED
            }
        }
    }

    enum class WinLoseOrDraw(private val str: String, val playScore: Int) {
        WIN("Z", 6),
        LOSE("X", 0),
        DRAW("Y", 3),
        IGNORED("", 0);

        companion object {
            fun fromString(str: String): WinLoseOrDraw {
                return WinLoseOrDraw.values().firstOrNull { it.str == str } ?: IGNORED
            }
        }
    }

}