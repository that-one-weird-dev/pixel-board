package com.zeropixel.pixelboard.ui.components.colorpalette

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun QuickBar(
    modifier: Modifier = Modifier,

    currentColor: Color,
    colorPalette: List<Color>,
    onColorPick: (Color) -> Unit,

    columns: Int = 1,
) {
    val columnWidth = 40
    val spacing = 5
    val width = (columns * columnWidth + (columns - 1) * spacing).dp

    ElevatedCard(
        modifier = modifier,
        colors = CardDefaults.elevatedCardColors()
    ) {
        ColorPalette(
            modifier = Modifier
                .padding(10.dp)
                .width(width),

            currentColor,
            colorPalette,
            onColorPick,
            columns = columns
        )
    }
}