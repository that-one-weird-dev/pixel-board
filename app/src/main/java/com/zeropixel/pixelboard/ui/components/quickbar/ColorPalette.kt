package com.zeropixel.pixelboard.ui.components.quickbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColorPalette(
    currentColor: Color,
    palette: List<Color>,
    onColorPick: (Color) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        itemsIndexed(palette) { i, color ->
            val isSelected = color == currentColor

            val shape = when (i) {
                0 -> RoundedCornerShape(10.dp, 10.dp, 5.dp, 5.dp)
                palette.size - 1 -> RoundedCornerShape(5.dp, 5.dp, 10.dp, 10.dp)
                else -> RoundedCornerShape(5.dp)
            }

            Color(
                Modifier.clip(shape),
                isSelected,
                color
            ) {
                onColorPick(color)
            }
        }
    }
}

@Composable
private fun Color(
    modifier: Modifier,
    isSelected: Boolean,
    color: Color,
    onClick: () -> Unit,
) {
    val scale = if (isSelected) .9f else 1f

    Box(
        modifier = Modifier
            .scale(scale)
            .then(modifier)
            .aspectRatio(1f)
            .background(color)
            .clickable(onClick = onClick)
    )
}
