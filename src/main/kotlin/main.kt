import kotlinx.interop.wasm.dom.*
import kotlinx.wasm.jsinterop.*

const val gameLoopInterval = 1000;
var intervalSinceNewTetronimo = 0;

/**
 * This is the game loop that updates the Tetris board with the latest state of the Tetronimos
 * @param canvas The view to be updated
 */
fun tetrisGameLoop(canvas: Canvas) {

    if (intervalSinceNewTetronimo == 20 || Model.currentTetronimo.frozen) {
        intervalSinceNewTetronimo = 0;
        Model.createNewTetronimo()
    }

    Model.updateBoard(intervalSinceNewTetronimo)
    View(canvas).render()
    intervalSinceNewTetronimo++;
}

fun main(args: Array<String>) {
    val canvas = document.getElementById("game").asCanvas
    Model.createBoard()
    setInterval(gameLoopInterval) {
        tetrisGameLoop(canvas)
    }
}