package com.zeropixel.pixelboard.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import com.zeropixel.pixelboard.canvas.Layer
import com.zeropixel.pixelboard.canvas.LayerBitmap

const val DefaultScale = .85f

@Composable
fun PixelCanvas(
    width: Int,
    height: Int,

    layers: List<Layer>,
    onDrawStart: (Int, Int) -> Unit,
    onDraw: (Int, Int) -> Unit,
    onDrawEnd: () -> Unit,

    /// Used for re-rendering the component when needed
    @Suppress("UNUSED_PARAMETER") rerender: Boolean,
) {
    var scale by remember { mutableStateOf(DefaultScale) }
    var rotation by remember { mutableStateOf(0f) }
    var panning by remember { mutableStateOf(Offset(0f, 0f)) }

    var coordinates: LayoutCoordinates? = null
    var lastDragPosition = Offset(0f, 0f)

    fun invokeCallbackWithOffset(offset: Offset, callback: (Int, Int) -> Unit) {
        coordinates?.let {
            val canvasSize = it.size
            val x = offset.x / canvasSize.width * width
            val y = offset.y / canvasSize.height * height

            callback(x.toInt(), y.toInt())
        }
    }

    fun onDragStart(offset: Offset) {
        invokeCallbackWithOffset(offset, onDrawStart)
        lastDragPosition = offset
    }

    fun onDrag(@Suppress("UNUSED_PARAMETER") change: PointerInputChange, offset: Offset) {
        val newPosition = lastDragPosition.plus(offset)

        invokeCallbackWithOffset(newPosition, onDraw)
        lastDragPosition = newPosition
    }

    fun onDragEnd() {
        onDrawEnd()
    }

    fun onTap(offset: Offset) {
        invokeCallbackWithOffset(offset, onDrawStart)
        invokeCallbackWithOffset(offset, onDraw)
        onDrawEnd()
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
        Box(
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
                    detectDragGestures(
                        onDrag = ::onDrag,
                        onDragStart = ::onDragStart,
                        onDragEnd = ::onDragEnd
                    )
                }
                .pointerInput(Unit) {
                    detectTapGestures(onTap = ::onTap)
                }
                .onGloballyPositioned { coordinates = it }
                .aspectRatio(1f)
                .shadow(20.dp)
                .border(2.dp, Color.Black)
                .padding(2.dp),
        ) {
            val backgroundBitmap = LayerBitmap.backgroundGrid(
                width = width,
                height = height,
                cellSize = 16,
            )
            Image(
                modifier = Modifier.fillMaxSize(),

                painter = BitmapPainter(
                    image = backgroundBitmap.asImageBitmap(),
                    filterQuality = FilterQuality.None,
                ),
                contentDescription = "Background"
            )

            layers.forEach { layer ->
                if (layer.visible) {
                    Image(
                        modifier = Modifier.fillMaxSize(),

                        painter = BitmapPainter(
                            image = layer.bitmap.asImageBitmap(),
                            filterQuality = FilterQuality.None,
                        ),
                        contentDescription = "Canvas",
                    )
                }
            }
        }

        val canReset = rotation != 0f
        ResetButton(visible = canReset) {
            rotation = 0f
        }
    }
}

@Composable
fun BoxScope.ResetButton(
    visible: Boolean,
    onClick: () -> Unit,
) {
    AnimatedVisibility(
        modifier = Modifier
            .align(Alignment.TopEnd),
        visible = visible,
    ) {
        FloatingActionButton(
            modifier = Modifier
                .padding(10.dp),

            onClick = onClick,
        ) {
            Icon(imageVector = Icons.Rounded.Refresh, contentDescription = null)
        }
    }
}