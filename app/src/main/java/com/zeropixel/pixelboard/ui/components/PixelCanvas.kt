package com.zeropixel.pixelboard.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp

@Composable
fun PixelCanvas(
    bitmap: ImageBitmap,
    onPixelDraw: (Int, Int) -> Unit,
) {
    var scale by remember { mutableStateOf(.85f) }
    var rotation by remember { mutableStateOf(0f) }

    var coordinates: LayoutCoordinates? = null
    var lastDragPosition = Offset(0f, 0f)

    fun drawPixel(offset: Offset) {
        coordinates?.let {
            val canvasSize = it.size

            val x = offset.x / canvasSize.width * bitmap.width
            val y = offset.y / canvasSize.height * bitmap.height

            onPixelDraw(x.toInt(), y.toInt())
        }
    }

    fun onDragStart(offset: Offset) {
        lastDragPosition = offset
    }

    fun onDrag(change: PointerInputChange, offset: Offset) {
        val newPosition = Offset(lastDragPosition.x + offset.x, lastDragPosition.y + offset.y)

        drawPixel(newPosition)
        lastDragPosition = newPosition
    }

    Image(
        modifier = Modifier
            .scale(maxOf(.5f, minOf(3f, scale)))
            .rotate(rotation)
            .pointerInput(Unit) {
                detectTransformGestures(true) { _, _, zoom, rot ->
                    scale *= zoom
                    rotation += rot
                }
            }
            .pointerInput(Unit) {
                detectDragGestures(onDrag = ::onDrag, onDragStart = ::onDragStart)
            }
            .pointerInput(Unit) {
                detectTapGestures(onTap = ::drawPixel)
            }
            .onGloballyPositioned { coordinates = it }
            .aspectRatio(1f)
            .shadow(15.dp)
            .border(1.dp, Color(0xff4c566a))
            .background(Color.White),

        painter = BitmapPainter(
            image = bitmap,
            filterQuality = FilterQuality.None,
        ),
        contentDescription = "Canvas",
    )
}