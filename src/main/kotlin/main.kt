import kotlinx.interop.wasm.dom.*
import kotlinx.wasm.jsinterop.*

const val gameLoopInterval = 1000;
var gameCounter = 0;

/**
 * This is the game loop that updates the Tetris board with the latest state of the Tetronimos
 * @param canvas The view to be updated
 */
fun tetrisGameLoop(canvas: Canvas) {
    Model.updateBoard(gameCounter)
    View(canvas).render()
    gameCounter++;
}

fun main(args: Array<String>) {
    val canvas = document.getElementById("game").asCanvas

    Model.createBoard()

    setInterval(gameLoopInterval) {

        tetrisGameLoop(canvas)

        if (gameCounter == 20) {
            gameCounter = 0;
        }
    }
}