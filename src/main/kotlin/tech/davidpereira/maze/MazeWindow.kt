package tech.davidpereira.maze

import java.awt.Graphics
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*

const val WINDOW_WIDTH = 600
const val WINDOW_HEIGHT = 600
const val TITLE_BAR_HEIGHT = 22
const val CELL_SIZE = 30

class MazeWindow : JFrame() {

    private val maze = Maze(WINDOW_WIDTH / CELL_SIZE, WINDOW_HEIGHT / CELL_SIZE)

    init {
        title = "Maze Generator"
        isVisible = true
        isResizable = false
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE

        contentPane = object : JPanel(), ActionListener {

            init {
                Timer(100, this).start()
            }

            override fun paintComponent(g: Graphics) {
                maze.generate()
                maze.show(g)
            }

            override fun actionPerformed(e: ActionEvent?) {
                repaint()
            }
        }

        setSize(WINDOW_WIDTH, WINDOW_HEIGHT + TITLE_BAR_HEIGHT)
    }

}

fun main() {
    SwingUtilities.invokeLater { MazeWindow() }
}