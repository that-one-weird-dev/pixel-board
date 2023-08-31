package com.zeropixel.pixelboard.canvas

import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.get
import com.zeropixel.pixelboard.canvas.utils.ColorInt

class LayerBitmap(
    val width: Int,
    val height: Int,
    fillColor: ColorInt = 0x00000000,
) {

    private val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888, false)

    init {
        fillPixels(fillColor)
    }

    fun setPixel(x: Int, y: Int, color: ColorInt): ColorInt {
        if (x < 0 || x >= bitmap.width || y < 0 || y >= bitmap.height) return color

        val oldColor = bitmap[x, y]

        bitmap.setPixel(x, y, color)

        return oldColor
    }

    fun getPixel(x: Int, y: Int): ColorInt {
        if (x < 0 || x >= bitmap.width || y < 0 || y >= bitmap.height) return 0x00000000

        return bitmap.getPixel(x, y)
    }

    fun asImageBitmap(): ImageBitmap = bitmap.asImageBitmap()

    private fun fillPixels(color: ColorInt) {
        repeat(width) { x ->
            repeat(height) { y ->
                setPixel(x, y, color)
            }
        }
    }

    companion object {
        fun backgroundGrid(width: Int, height: Int, cellSize: Int): LayerBitmap {
            val bitmap = LayerBitmap(width, height)

            for (x in 0..width) {
                for (y in 0..height) {
                    val isDarkCellHorizontally = x / cellSize == 0
                    val isDarkCellVertically = y / cellSize == 0

                    val color = if (isDarkCellHorizontally == isDarkCellVertically) {
                        0xffcacacc.toInt()
                    } else {
                        0xfffdfdfd.toInt()
                    }

                    bitmap.setPixel(x, y, color)
                }
            }

            return bitmap
        }
    }
}