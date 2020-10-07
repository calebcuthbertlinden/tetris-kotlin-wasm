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

    fun poly(x1: Int, y11: Int, y12: Int, x2: Int, y21: Int, y22: Int, style: String) = with(context) {
        beginPath()
        lineWidth = 2; // In pixels
        setter("strokeStyle", style)
        setter("fillStyle", style)

        moveTo(x1, fieldHeight - y11)
        lineTo(x1, fieldHeight - y12)
        lineTo(x2, fieldHeight - y22)
        lineTo(x2, fieldHeight - y21)
        lineTo(x1, fieldHeight - y11)

        fill()

        closePath()
        stroke()
    }

    fun showValue(index: Int, color: String) = with(context) {
        val textCellHeight = teamHeight / Model.tupleSize
        val textBaseline = index * textCellHeight + textCellHeight / 2

        // The team number rectangle
        fillStyle = Style.teamNumberColor
        fillRect(teamOffsetX + teamPad,  teamHeight - textBaseline - teamRect/2, teamRect, teamRect)

        // The team number rectangle
        fillStyle = color
        fillRect(resultOffsetX,  teamHeight - textBaseline - teamRect/2, teamRect/2, teamRect)
    }

    fun showLegend() = with(context){
        setter("font", "16px monospace")
        setter("textAlign", "left")
        setter("textBaseline", "top")
        fillStyle = Style.fontColor

        fillText("-10 sec", legendPad, legendOffsetY + legendPad, legendWidth)
        setter("textAlign", "right")
        fillText("now", legendWidth - legendPad, legendOffsetY + legendPad, legendWidth)
    }

    fun scaleX(x: Int): Int {
        return x * fieldWidth / (Model.backLogSize - 2)
    }

    fun scaleY(y: Float): Int {
        return (y * fieldHeight).toInt()
    }

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
