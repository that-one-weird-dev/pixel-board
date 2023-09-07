package com.zeropixel.pixelboard.utils

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.core.graphics.set
import com.zeropixel.engine.LayerBitmap


fun LayerBitmap.toImageBitmap(): ImageBitmap {
    // TODO: The performance of this thing are horrible please remove for better solution
    val image = ImageBitmap(width, height)
    val bitmap = image.asAndroidBitmap()

    repeat(width) { x ->
        repeat(height) { y ->
            bitmap[x, y] = get(x, y)
        }
    }

    return image
}