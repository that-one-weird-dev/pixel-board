package com.zeropixel.pixelboard.canvas

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.get

private const val CIRCLE_RADIUS_ERROR = .1

class LayerBitmap(
    val width: Int,
    val height: Int,
    val backgroundColor: Color = Color.Transparent
) {

    private val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888, false)

    init {
        clearPixels()
    }

    fun clearPixels() {
        repeat(width) { x ->
            repeat(height) { y ->
                setPixel(x, y, backgroundColor)
            }
        }
    }

    /**
     * @return old color
     */
    fun setPixel(x: Int, y: Int, color: Color): Int {
        if (x < 0 || x >= bitmap.width || y < 0 || y >= bitmap.height) return color.toArgb()

        val oldColor = bitmap[x, y]

        bitmap.setPixel(x, y, color.toArgb())

        return oldColor
    }

    fun getColor(x: Int, y: Int): Color? {
        if (x < 0 || x >= bitmap.width || y < 0 || y >= bitmap.height) return null

        return Color(bitmap.getPixel(x, y))
    }

    fun asImageBitmap(): ImageBitmap = bitmap.asImageBitmap()

    companion object {
        fun backgroundGrid(width: Int, height: Int, cellSize: Int): LayerBitmap {
            val bitmap = LayerBitmap(width, height)

            for (x in 0..width) {
                for (y in 0..height) {
                    val isDarkCellHorizontally = x / cellSize == 0
                    val isDarkCellVertically = y / cellSize == 0

                    val color = if (isDarkCellHorizontally == isDarkCellVertically) {
                        Color(0xffcacacc)
                    } else {
                        Color(0xfffdfdfd)
                    }

                    bitmap.setPixel(x, y, color)
                }
            }

            return bitmap
        }
    }
}