package com.zeropixel.pixelboard.ui.components.colorpalette

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
        ) {
            itemsIndexed(palette.colors) { i, color ->
                val isSelected = color == currentColor

                Color(
                    Modifier,
                    isSelected,
                    color,
                    onClick = { onColorPick(i) }
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(Color(currentColor))
                .border(2.dp, Color.Black),
        )
    }
}

@Composable
private fun Color(
    modifier: Modifier,
    isSelected: Boolean,
    color: ColorInt,
    onClick: () -> Unit,
) {
    val border = if (isSelected) 4.dp else 1.dp

    Box(
        modifier = Modifier
            .size(40.dp)
            .then(modifier)
            .background(Color(color))
            .clickable(onClick = onClick)
            .border(border, Color.Black)
    )
}
