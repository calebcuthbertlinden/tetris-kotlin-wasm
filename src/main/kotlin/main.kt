import kotlinx.interop.wasm.dom.*
import kotlinx.wasm.jsinterop.*

const val gameLoopInterval = 1000;

/**
 * This is the game loop that updates the Tetris board with the latest state of the Tetronimos
 * @param canvas The view to be updated
 */
fun tetrisGameLoop() {
    Model.updateBoard()
    renderView()
}

fun renderView() {
    View(document.getElementById("game").asCanvas).render()
}

fun main(args: Array<String>) {
    Model.createBoard()
    setInterval(gameLoopInterval) {
        tetrisGameLoop()
    }

    document.getElementById("move-left").setter("onclick") {
        Model.updateLocation(true)
        renderView()
    }

    document.getElementById("move-right").setter("onclick") {
        Model.updateLocation(false)
        renderView()
    }

    document.getElementById("rotate").setter("onclick") {
        Model.flipTetromino()
        renderView()
    }
}