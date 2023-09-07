package com.zeropixel.pixelboard.components.colorpalette

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zeropixel.pixelboard.components.shared.Panel
import com.zeropixel.pixelboard.components.colorpalette.ColorPalette as ColorPaletteComponent

@Composable
fun QuickBar(
    modifier: Modifier = Modifier,

    currentColor: com.zeropixel.engine.utils.ColorInt,
    colorPalette: com.zeropixel.engine.palette.ColorPalette,
    onColorPick: (com.zeropixel.engine.utils.ColorInt) -> Unit,

    columns: Int = 1,
) {
    val columnWidth = 40
    val spacing = 5
    val width = (columns * columnWidth + (columns - 1) * spacing).dp

    Panel(modifier) {
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