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

    val tupleSize = 5
    val styles = Style.styles

    val backLogSize = 100
    private val backLog = IntArray(backLogSize * tupleSize, {0})
    private fun offset(time: Int, color: Int) = time * tupleSize + color

    var counter = 0
    var current = 0
    var maximal = 0

    fun colors(time: Int, color: Int): Int = backLog[offset(time, color)]

    fun tuple(time: Int) = backLog.slice(time * tupleSize .. (time + 1) * tupleSize - 1)

    fun createBoard() {
        var row = 0
        var column = 0
        boardTiles = Array(boardRows) {
            Array(boardColumns) {
                BoardTile(false, row, column)
            }
        }
    }

    fun updateBoard(new: Array<Int>, counterNew: Int) {
        new.forEachIndexed { index, it ->
            backLog[offset(current, index)] = it
        }
        current = (current+1) % backLogSize

        new.forEach {
            if (it > maximal) maximal = it
        }

        counter = counterNew
    }

    fun updateTetrominoLocation() {

    }

    fun flipTetromino() {

    }
}
