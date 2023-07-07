package com.zeropixel.pixelboard.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import com.zeropixel.pixelboard.ui.theme.Colors

const val DefaultScale = .85f

@Composable
fun PixelCanvas(
    bitmap: ImageBitmap,
    onPixelDraw: (Int, Int) -> Unit,
) {
    var scale by remember { mutableStateOf(DefaultScale) }
    var rotation by remember { mutableStateOf(0f) }
    var panning by remember { mutableStateOf(Offset(0f, 0f)) }

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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures(true) { _, pan, zoom, rot ->
                    scale *= zoom
                    rotation += rot
                    panning += pan
                }
            }
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .graphicsLayer {
                    translationX = panning.x
                    translationY = panning.y
                    scaleX = maxOf(.5f, minOf(3f, scale))
                    scaleY = maxOf(.5f, minOf(3f, scale))
                    rotationZ = rotation
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
                .border(1.dp, Colors.Dark4)
                .background(Color.White),

            painter = BitmapPainter(
                image = bitmap,
                filterQuality = FilterQuality.None,
            ),
            contentDescription = "Canvas",
        )

        val canReset = rotation != 0f
        AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.TopEnd),
            visible = canReset
        ) {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Colors.Dark2)
                    .border(1.dp, Colors.Dark4, RoundedCornerShape(10.dp))
                    .clickable {
                        rotation = 0f
                    }
            ) {
                Icon(
                    modifier = Modifier.size(50.dp),
                    imageVector = Icons.Rounded.Refresh,
                    contentDescription = null,
                    tint = Colors.White1,
                )
            }
        }
    }
}