import kotlinx.interop.wasm.dom.*
import kotlinx.wasm.jsinterop.*

/**
 *
 * @property boardTiles a 2D array of the Tetris game board
 * @property tileWidth width of the tile
 * @property tileHeight height of tile
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

    // current falling Tetronimo
    var currentTetronimo: Tetronimo = Tetronimo("", 0, 0, 0, 0, false, "#7a6aea", TetronimoType.SQUARE)

    /**
     * Create the Tetris board to a set number of tiles based on rows and collumns. Initialise the first Tetronimo
     */
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
        currentTetronimo = Tetronimo("L", 100, tileHeight, tileWidth, tileHeight, false, "#7a6aea", TetronimoType.SQUARE)
    }

    /**
     * Move the current tetronimo down the board.
     * Initialise a new one if need be and add the previously frozen one to the frozen list
     */
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

    /**
     * Method to check if the current Tetronimo is touching the bottom of the board
     * @return Boolean if the current tetronimo is touching another frozen one
     */
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

    /**
     * Create a new Tetronimo of one of the existing types
     */
    fun createNewTetronimo() {

        // TODO generate random start x modifier
        if (randomModifier + 3 > 6) {
            randomModifier = randomModifier - 3
        } else {
            randomModifier = randomModifier + 2
        }

        var localShape = TetronimoType.SQUARE
        when (randomModifier) {
            0 -> localShape = TetronimoType.DOWN_RIGHT_DOWN
            1 -> localShape = TetronimoType.SQUARE
            2 -> localShape = TetronimoType.UPSIDE_DOWN_LEFT
            3 -> localShape = TetronimoType.DOWN_LEFT_DOWN
            4 -> localShape = TetronimoType.UPSIDE_DOWN_RIGHT
            5 -> localShape = TetronimoType.FOUR_WIDE
            6 -> localShape = TetronimoType.PODIUM
            else -> {}
        }

        // TODO generate random color index
        var color = colors[randomModifier]
        currentTetronimo = Tetronimo("L", tileWidth, tileHeight, tileWidth * randomModifier, tileHeight, false, color, localShape);
    }

    /**
     * Move the Tetronimo one tile to either side, depending on the input
     */
    fun updateLocation(left: Boolean) {
        if (left) {
            currentTetronimo.xCoord = currentTetronimo.xCoord - tileWidth
        } else {
            currentTetronimo.xCoord = currentTetronimo.xCoord + tileWidth
        }
    }

    /**
     * Flip the Tetronimo 90 degrees
     */
    fun flipTetromino() {

    }
}
