import kotlinx.interop.wasm.dom.*
import kotlinx.wasm.jsinterop.*

object Style {
    val backgroundColor = "#16103f"
    val teamNumberColor = "#38335b"
    val fontColor = "#000000"
    val styles = arrayOf("#ff7616", "#f72e2e", "#7a6aea", "#4bb8f6", "#ffffff")
}

open class Layout(val rect: DOMRect)  {
    val lowerAxisLegend = 0.1
    val fieldPartHeight = 1.0 - lowerAxisLegend

    val teamNumber = 0.10
    val result = 0.20
    val fieldPartWidth = 1.0 - teamNumber - result

    val teamBackground = 0.05

    val legendPad = 50
    val teamPad = 50
    val resultPad = 40

    val teamRect = 50

    val rectLeft = rect.getInt("left")
    val rectTop = rect.getInt("top")
    val rectRight = rect.getInt("right")
    val rectBottom = rect.getInt("bottom")
    val rectWidth = rectRight - rectLeft
    val rectHeight = rectBottom - rectTop

    val fieldWidth: Int = (rectWidth.toFloat() * fieldPartWidth).toInt()
    val fieldHeight: Int = (rectHeight.toFloat() * fieldPartHeight).toInt()

    val teamWidth = (rectWidth.toFloat() * teamNumber).toInt()
    val teamOffsetX = fieldWidth
    val teamHeight = fieldHeight

    val resultWidth = (rectWidth.toFloat() * result).toInt()
    val resultOffsetX = fieldWidth + teamWidth
    val resultHeight = fieldHeight

    val legendWidth = fieldWidth
    val legendHeight = (rectWidth.toFloat() * lowerAxisLegend)
    val legendOffsetY = fieldHeight
}

class View(canvas: Canvas): Layout(canvas.getBoundingClientRect()) {
    val context = canvas.getContext("2d");

    fun clean() {
        var yCoord = 0
        for (rows in 0 until 20) {
            var xCoord = 0
            for (i in 0 until 10) {
                context.fillStyle = "#4bb8f6"
                context.fillRect(xCoord, yCoord, 40, 10);
                xCoord = xCoord + 40
            }
            yCoord = yCoord + 10
        }
    }

    fun render() {
        clean()

        context.fillStyle = "#7a6aea"
        context.fillRect(40, 10 * Model.counter, 40, 10);

        // show frozen tetronimos on board
        /* for (tetr in 0 until Model.tetronimos.size) {}*/

    }
}
