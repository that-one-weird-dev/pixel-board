package com.zeropixel.pixelboard.utils

import androidx.compose.ui.graphics.Color
import com.zeropixel.pixelboard.canvas.CanvasBitmap

fun GenerateBackgroundBitmap(width: Int, height: Int, cellSize: Int): CanvasBitmap {
    val bitmap = CanvasBitmap(width, height)

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