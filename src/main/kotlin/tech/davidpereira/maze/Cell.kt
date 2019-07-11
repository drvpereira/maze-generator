package tech.davidpereira.maze

import java.awt.Color
import java.awt.Graphics

class Cell(val x: Int, val y: Int) {

    private val walls = booleanArrayOf(true, true, true, true)

    var visited = false

    fun show(g: Graphics) {
        if (visited) {
            g.color = Color.RED
            g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE)
        }

        g.color = Color.BLACK
        if (walls[0]) g.drawLine(x * CELL_SIZE, y * CELL_SIZE, (x + 1) * CELL_SIZE, y * CELL_SIZE)
        if (walls[1]) g.drawLine((x + 1) * CELL_SIZE, y * CELL_SIZE, (x + 1) * CELL_SIZE, (y + 1) * CELL_SIZE)
        if (walls[2]) g.drawLine(x * CELL_SIZE, (y + 1) * CELL_SIZE, (x + 1) * CELL_SIZE, (y + 1) * CELL_SIZE)
        if (walls[3]) g.drawLine(x * CELL_SIZE, y * CELL_SIZE, x * CELL_SIZE, (y + 1) * CELL_SIZE)
    }

    fun removeWallTo(chosenCell: Cell) {
        when {
            this.y - chosenCell.y  ==  1 -> { this.walls[0] = false; chosenCell.walls[2] = false }
            this.y - chosenCell.y  == -1 -> { this.walls[2] = false; chosenCell.walls[0] = false }
            this.x - chosenCell.x  ==  1 -> { this.walls[3] = false; chosenCell.walls[1] = false }
            this.x - chosenCell.x  == -1 -> { this.walls[1] = false; chosenCell.walls[3] = false }
        }
    }

}

