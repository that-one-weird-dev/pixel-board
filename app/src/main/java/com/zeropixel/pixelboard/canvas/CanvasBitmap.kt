package com.zeropixel.pixelboard.canvas

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb

class CanvasBitmap(
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

    fun drawCircle(x: Int, y: Int, radius: Int, color: Color) {
        for (drawX in (x - radius)..(x + radius)) {
            for (drawY in (y - radius)..(y + radius)) {
                drawPixel(drawX, drawY, color)
            }
        }
    }

    fun drawPixel(x: Int, y: Int, color: Color) {
        if (x < 0 || x >= bitmap.width || y < 0 || y >= bitmap.height) return

        bitmap.setPixel(x, y, color.toArgb())
    }

    fun asImageBitmap(): ImageBitmap = bitmap.asImageBitmap()
}