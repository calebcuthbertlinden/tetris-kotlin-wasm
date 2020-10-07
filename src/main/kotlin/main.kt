import kotlinx.interop.wasm.dom.*
import kotlinx.wasm.jsinterop.*

const val gameLoopInterval = 1000;
var gameCounter = 0;

/**
 * This is the game loop that updates the Tetris board with the latest state of the Tetronimos
 * @param canvas The view to be updated
 */
fun tetrisGameLoop(canvas: Canvas) {
    fetch(updateBoard()).
            then { args: ArrayList<JsValue> ->
                val response = Response(args[0])
                response.json()
            }.
            then { args: ArrayList<JsValue> ->
                val json = args[0]
                val colors = JsArray(json.getProperty("colors"))
                assert(colors.size == Model.tupleSize)

                val tuple = arrayOf<Int>(0, 0, 0, 0, 0)
                for (i in 0 until colors.size) {
                    val color = colors[i].getInt("color")
                    val counter = colors[i].getInt("counter")
                    tuple[color - 1] = counter
                }

                Model.updateBoard(tuple, gameCounter)
            }.
            then { View(canvas).render() }

    gameCounter++;
}

/**
 * Updates the board based off the latest co-ordinates of the Tetronimos
 * @param canvas The view to be updated
 */
fun updateBoard(): String {
    return "/stats.json";
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