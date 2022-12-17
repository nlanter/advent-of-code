package org.elwaxoro.advent.y2022

import org.elwaxoro.advent.PuzzleDayTester

class Dec11 : PuzzleDayTester(11, 2022) {

    override fun part1(): Any = loadTunnels().let {
        val paths = mutableListOf<Pair<MutableList<String>, Int>>()
        for (neighbor in it["AA"]!!.neighbors) {
            val q = mutableListOf("AA")
            q.add(neighbor)
            val x = dfs(it[neighbor]!!, 0, 30, it, mutableListOf("AA", neighbor), q)
            println("for $neighbor $x")
            q.remove(neighbor)
            paths.add(x)
        }

        paths
    }


    private fun dfs(
        tunnel: Tunnel,
        totalPressureRelief: Int,
        timeRemaining: Int,
        map: Map<String, Tunnel>,
        openValves: MutableList<String>,
        q: MutableList<String>
    ): Pair<MutableList<String>, Int> {
        if (timeRemaining <= 0) return openValves to totalPressureRelief


        for (neighbor in tunnel.neighbors.map { map[it]!! }) {
            if (q.contains(neighbor.valve)) continue
            println("adding valve ${neighbor.valve}")
            q.add(neighbor.valve)
            println("checking neighbor ${neighbor.valve} for ${tunnel.valve} $totalPressureRelief. q is $q")
            val searched = if (neighbor.flowRate == 0 || openValves.contains(neighbor.valve)) {
                dfs(neighbor, totalPressureRelief, timeRemaining - 1, map, openValves, q)
            } else dfs(neighbor, totalPressureRelief + (neighbor.flowRate * timeRemaining -2), timeRemaining -2, map, openValves.apply { add(neighbor.valve) }, q)

            q.remove(neighbor.valve)
            return searched
        }

        return openValves to totalPressureRelief //???
    }

    private fun findPath(
        tunnel: Tunnel,
        totalPressureRelief: Int,
        timeRemaining: Int,
        map: Map<String, Tunnel>,
        path: MutableList<String>
    ): Int {
        if (timeRemaining <= 0) return totalPressureRelief
        var timeRemainingTmp = timeRemaining

        for (neighbor in tunnel.neighbors.map { map[it]!! }) {
            println("checking neighbor ${neighbor.valve} for ${tunnel.valve} $totalPressureRelief")
//            if (!path.contains(neighbor.name)) {
                path.add(tunnel.valve)

                timeRemainingTmp -= 1 // travel time
                if (neighbor.flowRate > 0) {
                    timeRemainingTmp -= 1 // opening valve time
                } else {
                    continue
                }
                return findPath(
                    neighbor,
                    totalPressureRelief + (neighbor.flowRate * timeRemainingTmp),
                    timeRemainingTmp,
                    map,
                    path.apply { add(neighbor.valve) })

//            }
        }

        return totalPressureRelief
    }

    private fun loadTunnels() = load().map { it.toTunnel() }.associateBy { it.valve }


    private fun String.toTunnel(): Tunnel {
        val split = this.split(" ")
        return Tunnel(split[0], split[1].toInt(), split[2].split(","))
    }

    class Tunnel(val valve: String, val flowRate: Int, val neighbors: List<String>, var visited: Boolean = false) {

        @Override
        override fun toString(): String {
            return "$valve $flowRate -> $neighbors"
        }
    }
}