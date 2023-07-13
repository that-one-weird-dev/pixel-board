package com.zeropixel.pixelboard.canvas

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import kotlin.math.sqrt

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
                drawPixel(x, y, backgroundColor)
            }
        }
    }

    fun drawCircle(x: Int, y: Int, radius: Float, color: Color) {
        for (xOffset in (-radius.toInt())..radius.toInt()) {
            for (yOffset in (-radius.toInt())..radius.toInt()) {
                val distance = sqrt((xOffset * xOffset + yOffset * yOffset).toFloat())

                if (distance < radius + CIRCLE_RADIUS_ERROR) {
                    drawPixel(x + xOffset, y + yOffset, color)
                }
            }
        }
    }

    fun drawPixel(x: Int, y: Int, color: Color) {
        if (x < 0 || x >= bitmap.width || y < 0 || y >= bitmap.height) return

        bitmap.setPixel(x, y, color.toArgb())
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

                    bitmap.drawPixel(x, y, color)
                }
            }

            return bitmap
        }
    }
}