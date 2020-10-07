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

    //////////////////////////

    // The Tetris game board
    lateinit var boardTiles: Array<Array<BoardTile>>
    const val boardColumns = 10
    const val boardRows = 20

    // The Tetrominos in play
    var tetronimos: Array<Tetronimo>? = null
    var currentTetronimo: Tetronimo? = null

    //////////////////////////

    var counter = 0
    var current = 0
    var maximal = 0

    fun createBoard() {
        var row = 0
        var column = 0
        boardTiles = Array(boardRows) {
            Array(boardColumns) {
                BoardTile(false, row, column)
            }
        }
    }

    fun updateBoard(counterNew: Int) {
        counter = counterNew
    }

    fun updateTetrominoLocation() {

    }

    fun flipTetromino() {

    }
}
