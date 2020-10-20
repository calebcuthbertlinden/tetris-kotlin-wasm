import kotlinx.interop.wasm.dom.*
import kotlinx.wasm.jsinterop.*

const val gameLoopInterval = 1000;

/**
 * This is the game loop that updates the Tetris board with the latest state of the Tetronimos
 * @param canvas The view to be updated
 */
fun tetrisGameLoop() {
    Model.updateBoard()
    View(document.getElementById("game").asCanvas).render()
}

fun main(args: Array<String>) {
    Model.createBoard()
    setInterval(gameLoopInterval) {
        tetrisGameLoop()
    }
}