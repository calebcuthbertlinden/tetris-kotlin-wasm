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

    var randomModifier = -1

    // the frozen Tetrominos
    var tetronimos = arrayListOf<Tetronimo>()

    val colors = arrayOf("#ff7616", "#f72e2e", "#7a6aea", "#4bb8f6", "#ffffff", "#7a6aea", "#f72e2e")

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

        var rowCurrentCount = 0
        for (rowCurrent in currentTetronimo.shape) {

            // for each column in current falling tetronimos last row
            var columnCurrentCount = 0
            for (columnCurrent in rowCurrent) {

                // for each frozen tetronimo
                for (tetro in tetronimos) {

                    var rowFrozenCount = 0
                    for (rowFrozen in tetro.shape) {

                        var columnFrozenCount = 0
                        for (columnFrozen in rowFrozen) {

                            var currentXCoord = currentTetronimo.yCoord + (tileHeight * (rowCurrentCount + 1))
                            var currentYCoord = tetro.yCoord + (tileHeight * rowFrozenCount)
                            var frozenXCoord = currentTetronimo.xCoord + (columnCurrentCount * tileWidth)
                            var frozenYCoord = tetro.xCoord + (columnFrozenCount * tileWidth)

                            if (columnCurrent && columnFrozen && currentXCoord == currentYCoord && frozenXCoord == frozenYCoord) {
                                return true
                            }

                            columnFrozenCount++

                        }
                        rowFrozenCount++
                    }
                }
                columnCurrentCount++
            }

            rowCurrentCount++
        }

        return false
    }

    fun createNewTetronimo() {

        // TODO generate random start x modifier
        if (randomModifier + 3 > 6) {
            randomModifier = randomModifier - 3
        } else {
            randomModifier = randomModifier + 2
        }

        var localShape = square
        when (randomModifier) {
            0 -> localShape = downRightDown
            1 -> localShape = square
            2 -> localShape = upsideDownLeft
            3 -> localShape = downLeftDown
            4 -> localShape = upsideDownRight
            5 -> localShape = fourWide
            6 -> localShape = downRightDown
            else -> {
                print("x is neither 1 nor 2")
            }
        }

        // TODO generate random color index
        var color = colors[randomModifier]
        currentTetronimo = Tetronimo("L", tileWidth, tileHeight, tileWidth * randomModifier, tileHeight, false, color, localShape);
    }

    fun updateLocation() {
        currentTetronimo.xCoord = currentTetronimo.xCoord + tileWidth
    }

    fun getColor() {

    }

    fun flipTetromino() {

    }
}
