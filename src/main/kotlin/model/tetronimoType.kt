/**
 * shapes of tetronimos
 */
class TetronimoType {
    companion object {
        val SQUARE = arrayListOf(arrayListOf(true, true), arrayListOf(true, true))
        val UPSIDE_DOWN_LEFT = arrayListOf(arrayListOf(true, true), arrayListOf(true, true))
        val DOWN_LEFT_DOWN = arrayListOf(arrayListOf(false, true), arrayListOf(true, true), arrayListOf(true, false))
        val UPSIDE_DOWN_RIGHT = arrayListOf(arrayListOf(true, true), arrayListOf(true, false), arrayListOf(true, false))
        val FOUR_WIDE = arrayListOf(arrayListOf(true), arrayListOf(true), arrayListOf(true), arrayListOf(true))
        val PODIUM = arrayListOf(arrayListOf(false, true, false), arrayListOf(true, true, true))
        val DOWN_RIGHT_DOWN = arrayListOf(arrayListOf(true, false), arrayListOf(true, true), arrayListOf(false, true))
    }
}