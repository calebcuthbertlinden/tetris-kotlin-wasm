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

    // the frozen Tetrominos
    var tetronimos: Array<Tetronimo>? = null
    // current falling Tetronimo
    var currentTetronimo: Tetronimo = Tetronimo("", 0, 0, 0, 0, false, "#7a6aea")

    var counter = 0

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
        currentTetronimo = Tetronimo("L", 100, tileHeight, tileWidth, tileHeight, false, "#7a6aea")
    }

    fun updateBoard(updatedCounter: Int) {
        counter = updatedCounter
        currentTetronimo.yCoord = tileHeight * Model.counter
    }

    fun createNewTetronimo() {
        currentTetronimo = Tetronimo("L", tileWidth, tileHeight, tileWidth, tileHeight, false, "#7a6aea");
    }

    fun updateTetrominoLocation() {

    }

    fun flipTetromino() {

    }
}
