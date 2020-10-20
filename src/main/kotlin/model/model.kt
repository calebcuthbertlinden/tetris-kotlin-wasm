import kotlinx.interop.wasm.dom.*
import kotlinx.wasm.jsinterop.*

/**
 *
 * @property boardTiles a 2D array of the Tetris game board
 * @property boardColumns amount of columns
 * @property boardRows amount of rows
 *
 * @property tetrominos all the Tetrominos in play
 * @property currentTetromino the current descending Tetromino
 */
object Model {

    // the Tetris game board
    lateinit var boardTiles: Array<Array<BoardTile>>
    const val boardColumns = 10
    const val boardRows = 20
    const val tileWidth = 40
    const val tileHeight = 10
    var distanceFromTop = 0;

    // the frozen Tetrominos
    var tetronimos = arrayListOf<Tetronimo>()

    val colors = arrayOf("#ff7616", "#f72e2e", "#7a6aea", "#4bb8f6", "#ffffff")

    val square = arrayListOf(arrayListOf(true, true), arrayListOf(true, true))
    val upsideDownLeft = arrayListOf(arrayListOf(true, true), arrayListOf(true, true))
    val downLeftDown = arrayListOf(arrayListOf(false, true), arrayListOf(true, true), arrayListOf(true, false))
    val upsideDownRight = arrayListOf(arrayListOf(true, true), arrayListOf(true, false), arrayListOf(true, false))
    val fourWide = arrayListOf(arrayListOf(true), arrayListOf(true), arrayListOf(true), arrayListOf(true))
    val podium = arrayListOf(arrayListOf(false, true, false), arrayListOf(true, true, true))
    val downRightDown = arrayListOf(arrayListOf(true, false), arrayListOf(true, true), arrayListOf(false, true))

    // current falling Tetronimo
    var currentTetronimo: Tetronimo = Tetronimo("", 0, 0, 0, 0, false, "#7a6aea", square)

    fun createBoard() {
        var row = 0
        var column = 0

        boardTiles = Array(boardRows) {
            row++
            Array(boardColumns) {
                column++
                BoardTile(false, row, column)
            }
        }

        // first tetronimo on the board
        currentTetronimo = Tetronimo("L", 100, tileHeight, tileWidth, tileHeight, false, "#7a6aea", square)
    }

    fun updateBoard() {
        if (currentTetronimo.frozen) {
            distanceFromTop = 0
            createNewTetronimo()
        }

        currentTetronimo.yCoord = tileHeight * distanceFromTop

        if (distanceFromTop == 14 || isTouchingCurrentTetronimo()) {
            currentTetronimo.frozen = true
            if (tetronimos.size < 14) {
                tetronimos.add(currentTetronimo)
            }
        }

        distanceFromTop++
    }

    fun isTouchingCurrentTetronimo(): Boolean {
        for (tetro in tetronimos) {
            var rowCount = 0
            for (row in currentTetronimo.shape) {
                rowCount++
            }

            for (column in currentTetronimo.shape[rowCount - 1]) {
                if (column &&
                        (currentTetronimo.yCoord + (tileHeight * rowCount) == tetro.yCoord) &&
                        (currentTetronimo.xCoord == tetro.xCoord)) {
                    return true
                }
            }
        }
        return false
    }

    fun createNewTetronimo() {

        // TODO generate random start x modifier
        val x = 1

        // TODO generate random color index
        var color = "#4bb8f6"

        currentTetronimo = Tetronimo("L", tileWidth, tileHeight, tileWidth * x, tileHeight, false, color, podium);
    }

    fun getColor() {

    }

    fun flipTetromino() {

    }
}
