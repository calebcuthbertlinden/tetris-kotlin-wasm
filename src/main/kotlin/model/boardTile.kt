/**
 *
 * @property isCovered Boolean Width of the Tetromino according to amount of blocks
 * @property xCoord x coordinate of Tetromino on the Tetris board
 * @property yCoord y coordinate of Tetromino on the Tetris board
 */
data class BoardTile(
        var isCovered: Boolean = false,
        var xCoord: Int,
        var yCoord: Int
)