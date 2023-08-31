package com.zeropixel.pixelboard.ui.components.colorpalette

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.zeropixel.pixelboard.canvas.palette.ColorPalette
import com.zeropixel.pixelboard.canvas.utils.ColorInt

@Composable
fun ColorPalette(
    modifier: Modifier = Modifier,
    currentColor: ColorInt,
    palette: ColorPalette,
    onColorPick: (ColorInt) -> Unit,

    columns: Int,
) {
    LazyVerticalGrid(
        modifier = modifier,

        columns = GridCells.Fixed(columns),

        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        itemsIndexed(palette.colors) { i, color ->
            val isSelected = color == currentColor

            val topLeftCorner = if (i == 0) 10.dp else 5.dp
            val topRightCorner = if (i == columns - 1) 10.dp else 5.dp
            val bottomLeftCorner = if (i == palette.size - 1 - (columns - 1)) 10.dp else 5.dp
            val bottomRightCorner = if (i == palette.size - 1) 10.dp else 5.dp

            val shape = RoundedCornerShape(
                topLeftCorner,
                topRightCorner,
                bottomRightCorner,
                bottomLeftCorner,
            )

            Color(
                Modifier.clip(shape),
                isSelected,
                color,
                onClick = { onColorPick(i) }
            )
        }
    }
}

@Composable
private fun Color(
    modifier: Modifier,
    isSelected: Boolean,
    color: ColorInt,
    onClick: () -> Unit,
) {
    val scale = if (isSelected) .9f else 1f

    Box(
        modifier = Modifier
            .size(40.dp)
            .scale(scale)
            .then(modifier)
            .aspectRatio(1f)
            .background(androidx.compose.ui.graphics.Color(color))
            .clickable(onClick = onClick)
    )
}
