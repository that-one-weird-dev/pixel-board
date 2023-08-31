package com.zeropixel.pixelboard.ui.components.colorpalette

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zeropixel.pixelboard.canvas.palette.ColorPalette
import com.zeropixel.pixelboard.canvas.utils.ColorInt
import com.zeropixel.pixelboard.ui.components.colorpalette.ColorPalette as ColorPaletteComponent

@Composable
fun QuickBar(
    modifier: Modifier = Modifier,

    currentColor: ColorInt,
    colorPalette: ColorPalette,
    onColorPick: (ColorInt) -> Unit,

    columns: Int = 1,
) {
    val columnWidth = 40
    val spacing = 5
    val width = (columns * columnWidth + (columns - 1) * spacing).dp

    ElevatedCard(
        modifier = modifier,
        colors = CardDefaults.elevatedCardColors()
    ) {
        ColorPaletteComponent(
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