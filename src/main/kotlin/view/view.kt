import kotlinx.interop.wasm.dom.*
import kotlinx.wasm.jsinterop.*

object Style {
    val backgroundColor = "#000000"
    val fontColor = "#000000"
    val styles = arrayOf("#ff7616", "#f72e2e", "#7a6aea", "#4bb8f6", "#ffffff")
}

class View(canvas: Canvas){
    val context = canvas.getContext("2d");

    fun clean() {
        var localBoardTiles = Model.boardTiles
        var yCoord = 0

        // setup tetris board
        for (rows in localBoardTiles) {
            var xCoord = 0
            for (columns in rows) {
                context.fillStyle = Style.backgroundColor
                context.fillRect(
                        xCoord,
                        yCoord,
                        Model.tileWidth,
                        Model.tileHeight);
                xCoord = xCoord + Model.tileWidth
            }
            yCoord = yCoord + Model.tileHeight
        }

        for (tetr in Model.tetronimos) {
            context.fillStyle = tetr.color
            var rowCount = 0
            for (row in tetr.shape) {
                var collumnCount = 0
                for (column in row) {
                    if (column) {
                        context.fillRect(
                                tetr.xCoord + (collumnCount * Model.tileWidth),
                                tetr.yCoord + (rowCount * Model.tileHeight),
                                Model.tileWidth,
                                Model.tileHeight)
                    }
                    collumnCount++
                }
                rowCount++
            }
        }
    }

    fun render() {
        clean()

        // show current tetronimo falling
        context.fillStyle = Model.currentTetronimo.color

        var rowCount = 0
        for (row in Model.currentTetronimo.shape) {
            var collumnCount = 0
            for (column in row) {
                if (column) {
                    context.fillRect(
                            Model.currentTetronimo.xCoord + (collumnCount * Model.tileWidth),
                            Model.currentTetronimo.yCoord + (rowCount * Model.tileHeight),
                            Model.tileWidth,
                            Model.tileHeight)
                }
                collumnCount++
            }
            rowCount++
        }
    }
}
