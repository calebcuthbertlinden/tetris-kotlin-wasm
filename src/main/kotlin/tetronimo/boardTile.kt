/**
 *
 * @property isCovered Boolean Width of the Tetromino according to amount of blocks
 * @property xCoord x coordinate of Tetromino on the Tetris board
 * @property yCoord y coordinate of Tetromino on the Tetris board
 */
data class BoardTile(
    val isCovered: Boolean = false,
    val xCoord: Int?,
    val yCoord: Int?
)