/**
 *
 * @property readableName A displayable name for the TetrominoType
 * @property height Height of the Tetromino according to amount of blocks
 * @property width Width of the Tetromino according to amount of blocks
 * @property xCoord x coordinate of Tetromino on the Tetris board
 * @property yCoord y coordinate of Tetromino on the Tetris board
 * @property frozen is the tetronimo frozen on the board
 */
data class Tetronimo(
    var readableName: String = " ",
    var height: Int,
    var width: Int,
    var xCoord: Int,
    var yCoord: Int,
    var frozen: Boolean,
    var color: String,
    var shape: List<List<Boolean>>
)