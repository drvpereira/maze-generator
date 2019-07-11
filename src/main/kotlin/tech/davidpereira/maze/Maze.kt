package tech.davidpereira.maze

import java.awt.Graphics
import java.util.*

class Maze(width: Int, height: Int) {

    private val grid = Array(height) { y -> Array(width) { x -> Cell(x, y) } }
    private val stack = ArrayDeque<Cell>()

    private var currentCell: Cell = grid[0][0]

    init {
        currentCell.visited = true
    }

    fun show(g: Graphics) {
        grid.forEach { i -> i.forEach { j -> j.show(g) } }
    }

    fun generate() {
        val chosenNeighbour = chooseNeighbour()

        if (chosenNeighbour != null) {

            chosenNeighbour.visited = true
            stack.push(currentCell)
            currentCell.removeWallTo(chosenNeighbour)
            currentCell = chosenNeighbour

        } else if (!stack.isEmpty()) {
            currentCell = stack.pop()
        }
    }

    private fun chooseNeighbour(): Cell? {
        return listOf(
            Pair(currentCell.x + 1, currentCell.y),
            Pair(currentCell.x - 1, currentCell.y),
            Pair(currentCell.x, currentCell.y - 1),
            Pair(currentCell.x, currentCell.y + 1)
        ).filter { it.first in 0..19 && it.second in 0..19 }
            .map { grid[it.second][it.first] }
            .filter { !it.visited }
            .shuffled().firstOrNull()
    }

}
