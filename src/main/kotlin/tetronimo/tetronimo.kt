/**
 *
 * @property readableName A displayable name for the TetrominoType
 * @property height Height of the Tetromino according to amount of blocks
 * @property width Width of the Tetromino according to amount of blocks
 * @property xCoord x coordinate of Tetromino on the Tetris board
 * @property yCoord y coordinate of Tetromino on the Tetris board
 */
data class Tetronimo(
    val readableName: String = " ",
    val height: Int?,
    val width: Int?,
    val xCoord: Int?,
    val yCoord: Int?
)