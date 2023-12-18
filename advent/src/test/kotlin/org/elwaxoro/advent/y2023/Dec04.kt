package org.elwaxoro.advent.y2023

import org.elwaxoro.advent.PuzzleDayTester

class Dec04 : PuzzleDayTester(4, 2023) {

    //26914
    override fun part1(): Any = load(testNum = 1).parseToCards().sumOf { it.score }

    //13080971
    override fun part2(): Any = load(testNum = 2).parseToCards().also {
        it.onEach {
            println("${it.cardNumber} - ${it.matchingNumbers}")
        }
    }.let { cards ->
        var go = true
        var index = 0
        val mutableCards = mutableListOf<Card>().also { it.addAll(cards) }.groupByTo(mutableMapOf()) { it.cardNumber }
        while (go) {
            val cardsForCardNumber = mutableCards[index + 1] ?: break
            cardsForCardNumber.onEach { card ->
                if (card.cardNumber == cards.last().cardNumber) {
                    go = false
                } else {
                    // copy cards
                    for (cardNumToDupe in card.cardNumber + 1..card.cardNumber + card.matchingNumbers) {
                        mutableCards[cardNumToDupe]?.get(0)?.also {
                            mutableCards[cardNumToDupe]?.add(it.copy())
                        }
                    }
                }
            }
            index++
        }
        mutableCards.entries.sumOf { it.value.size }
    }

    fun List<String>.parseToCards(): List<Card> = mapIndexed { idx, str ->
        val (winningNumbers, hand) = str.split("|").let { (winningStr, handStr) ->
            fun String.convertToInts() = trim().split(" ").filterNot { it.isEmpty() }.map { it.toInt() }
            winningStr.convertToInts() to handStr.convertToInts()
        }
        Card(winningNumbers, hand, idx + 1)
    }

    data class Card(val winningNumbers: List<Int>, val hand: List<Int>, val cardNumber: Int) {
        val matchingNumbers by lazy { hand.count { winningNumbers.contains(it) } }
        val score by lazy { if (matchingNumbers == 0) 0.0 else Math.pow(2.0, (matchingNumbers - 1).toDouble()) }
    }
}
