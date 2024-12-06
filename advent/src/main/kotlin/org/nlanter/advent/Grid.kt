package org.nlanter.advent

import org.elwaxoro.advent.Dir
import org.elwaxoro.advent.Turn

class Grid(input: List<String>, startChar: Char? = null, initialDir: Dir? = null) {
    private var currentCoordinate: Coordinate
    private var currentDir: Dir? = null
    private val coordinates: List<List<Coordinate>>

    init {
        this.coordinates = toCoordinates(input)
        this.currentCoordinate = startChar?.let { coordinates.findSingleChar(it) } ?: coordinates.first().first()
        this.currentCoordinate.markVisited()
        this.currentDir = initialDir
    }

    private fun toCoordinates(list: List<String>) = list.foldIndexed(mutableListOf<List<Coordinate>>()) { idx, acc, str ->
        acc.add(str.mapIndexed { colIdx, col ->
            Coordinate(x = colIdx, y = idx, d = col)
        })
        acc
    }

    private fun List<List<Coordinate>>.findSingleChar(char: Char) = this.flatMap { it.filter { it.d == char } }.firstOrNull()

    fun turn(turn: Turn) {
        currentDir = currentDir?.turn(turn)
    }
    fun nextMove(): Coordinate? = when (this.currentDir) {
            Dir.N -> this.getCoordinate(this.currentCoordinate.x, this.currentCoordinate.y - 1)
            Dir.S -> this.getCoordinate(this.currentCoordinate.x, this.currentCoordinate.y + 1)
            Dir.E -> this.getCoordinate(this.currentCoordinate.x + 1, this.currentCoordinate.y)
            Dir.W -> this.getCoordinate(this.currentCoordinate.x - 1, this.currentCoordinate.y)
            null -> throw IllegalStateException("No direction set")
        }

    fun move(): Coordinate? {
        val new = when (this.currentDir) {
            Dir.N -> this.getCoordinate(this.currentCoordinate.x, this.currentCoordinate.y - 1)
            Dir.S -> this.getCoordinate(this.currentCoordinate.x, this.currentCoordinate.y + 1)
            Dir.E -> this.getCoordinate(this.currentCoordinate.x + 1, this.currentCoordinate.y)
            Dir.W -> this.getCoordinate(this.currentCoordinate.x - 1, this.currentCoordinate.y)
            null -> throw IllegalStateException("No direction set")
        }
        if (new != null) {
            swap(currentCoordinate, new)
            currentCoordinate = new
        }
        return new
    }

    private fun swap(currentCoordinate: Coordinate, new: Coordinate) {
        val tmpD = currentCoordinate.d
        currentCoordinate.update(new.d)
        new.update(tmpD)
        new.markVisited()
    }

    fun getCoordinate(x: Int, y: Int) = coordinates.flatten().firstOrNull { it.x == x && it.y == y }

    fun getCurrentCoordinate() = currentCoordinate
    /**
     * Creates a visual representation of collection of Coords, required for some puzzles
     */
    fun printify() {
        coordinates.onEach { x->
            x.onEach { y ->
                print(y.d)
            }
            println()
        }
    }
}

data class Coordinate(val x: Int = 0, val y: Int = 0, var d: Char? = null, var visited: Boolean = false) {
    fun update(d: Char? = null) {
        this.d = d
    }
    fun markVisited() {
        this.visited = true
    }
}